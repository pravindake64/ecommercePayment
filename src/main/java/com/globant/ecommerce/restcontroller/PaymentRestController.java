package com.globant.ecommerce.restcontroller;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.globant.ecommerce.paymentdao.PaymentDAOImpl;
import com.globant.ecommerce.paymentfacade.PaymentFacadeImpl;
import com.globant.ecommerce.paymentmodel.Response;
import com.globant.ecommerce.paymentmodel.ResponseByID;
import com.globant.ecommerce.paymentmodel.Payment;
import com.globant.ecommerce.paymentmodel.Refund;

@RestController
public class PaymentRestController {
	@Autowired
	private PaymentFacadeImpl payimpl;

	@PostMapping("/order/payment")
	public Response makePayment(@RequestBody String data,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) throws JSONException {
		Response resp = new Response();
		if (payimpl.authenticate(authToken)) {
			JSONObject jo = null;
			JSONObject jcard = null;
			JSONObject netbanking = null;
			Payment payment = new Payment();
			Map<String, String> res = new HashMap<String, String>();
			jo = new JSONObject(data);
			payment.setUserid(jo.getInt("userid"));
			payment.setOrderid(jo.getInt("orderid"));
			payment.setPaymentmode(jo.getInt("paymentmode"));
			jcard = new JSONObject(jo.getString("card"));
			payment.setCardno(jcard.getString("cardnumber"));
			payment.setExpdate(jcard.getString("expdate"));
			payment.setCvv(jcard.getString("cvv"));
			payment.setCod(jo.getInt("cod"));
			netbanking = new JSONObject(jo.getString("onlinebanking"));
			payment.setBankname(netbanking.getString("bankname"));
			payment.setUsername(netbanking.getString("username"));
			payment.setPassword(netbanking.getString("password"));
			payment.setUpi(jo.getString("upi"));
			long transactionid = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			payment.setTransactionid(transactionid);
			payment.setPayableamount(jo.getInt("payableamount"));

			int n = payimpl.makePayment(payment);
			res.put("orderid", jo.getString("orderid"));
			if (n != 1) {
				res.put("paymentstatus", "terminated");
				res.put("trasactionid", transactionid + "");
				resp.setMessage("Payment UnSuccessful");
				resp.setStatus(400);
				resp.setData(res);
			} else {
				payimpl.updatePaymentStatus(transactionid);
				res.put("trasactionid", transactionid + "");
				res.put("paymentstatus", "Done");
				resp.setMessage("Payment Successful");
				resp.setStatus(200);
				resp.setData(res);
			}

			return resp;
		}

		resp.setMessage("User Not Logged In");
		resp.setStatus(401);
		return resp;
	}

	@GetMapping("/order/payment/{transactionid}")
	public ResponseByID getPayDetailsById(@PathVariable("transactionid") long transactionid,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) throws Exception {

		ResponseByID resp = new ResponseByID();
		if (payimpl.authenticate(authToken)) {
			Payment paymentdata = null;
			paymentdata = payimpl.getPayDeatilsById(transactionid);
			if (paymentdata.equals(null)) {
				resp.setMessage("Record not found");
				resp.setStatus(400);
				resp.setData(paymentdata);
			} else {
				resp.setMessage("Record Fetched Successful");
				resp.setStatus(200);
				resp.setData(paymentdata);
			}
			return resp;

		}
		resp.setMessage("User Not Logged In");
		resp.setStatus(401);
		return resp;
	}

	@PostMapping("/order/payment/refund")
	public Response getRefund(@RequestBody String data,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) throws JSONException {
		Response res = new Response();
		if (payimpl.authenticate(authToken)) {
			Refund refund = new Refund();
			JSONObject refdata = null;
			refdata = new JSONObject(data);
			Map<String, String> refunddata = new HashMap<String, String>();

			refund.setTransactionid(refdata.getLong("transactionid"));
			refund.setBankname(refdata.getString("bankname"));
			refund.setAccountno(refdata.getString("accountno"));
			refund.setIfsc(refdata.getString("ifsc"));
			refund.setPayableamount(refdata.getString("payableamount"));

			int refres = payimpl.getRefund(refund);
			if (refres != 1) {
				res.setMessage("Refund Unsuccessfully");
				res.setStatus(400);
				refunddata.put("transactionid", refdata.getLong("transactionid") + "");
				refunddata.put("refundstatus", "terminated");
				res.setData(refunddata);

			} else {
				payimpl.updateRefundStatus(refdata.getLong("transactionid"));
				res.setMessage("Refund Delivered Successfully");
				res.setStatus(200);
				refunddata.put("transactionid", refdata.getLong("transactionid") + "");
				refunddata.put("refundstatus", "Done");
				res.setData(refunddata);

			}

			return res;
		}
		res.setMessage("User Not Logged In");
		res.setStatus(401);
		return res;
	}

}
