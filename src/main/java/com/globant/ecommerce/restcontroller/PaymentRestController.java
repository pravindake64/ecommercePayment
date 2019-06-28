package com.globant.ecommerce.restcontroller;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.netflix.client.http.HttpResponse;

import springfox.documentation.swagger.readers.operation.ResponseHeaders;

import com.globant.ecommerce.paymentmodel.Payment;
import com.globant.ecommerce.paymentmodel.Refund;

@RestController
public class PaymentRestController {
	@Autowired
	private PaymentFacadeImpl payimpl;

	@PostMapping("/order/payment")
	public ResponseEntity<Map<String, String>> makePayment(@RequestBody String data,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken)
					throws JSONException {//   (, ResponseHeaders req)
			
		//Response resp = new Response();
		HttpHeaders header = new HttpHeaders();
		Payment payment = new Payment();
		Map<String, String> res = new HashMap<String, String>();

		if (payimpl.authenticate(authToken)) {
			JSONObject jo = null;
			JSONObject jcard = null;
			JSONObject netbanking = null;

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
				//resp.setMessage("Payment UnSuccessful");
				//resp.setStatus(400);
				header.add("messgae", "Payment Unsuccessfull");
				//resp.setData(res);
				return new ResponseEntity<>(res, header, HttpStatus.BAD_REQUEST);
			} else {
				payimpl.updatePaymentStatus(transactionid);
				res.put("trasactionid", transactionid + "");
				res.put("paymentstatus", "Completed");
				// resp.setMessage("Payment Successful");
				// resp.setStatus(200);
				// resp.setData(res);
				header.add("message", "Payment successfull");
				return new ResponseEntity<>(res, header, HttpStatus.OK);
			}

		}
		header.add("message", "User not loged in");
		//resp.setMessage("User Not Logged In");
		//resp.setStatus(401);
		return new ResponseEntity<>(res, header, HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/order/payment/{transactionid}")
	public ResponseEntity<Payment> getPayDetailsById(@PathVariable("transactionid") long transactionid,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) throws Exception {

		//ResponseByID resp = new ResponseByID();
		HttpHeaders header = new HttpHeaders();
		Payment paymentdata = null;
		if (payimpl.authenticate(authToken)) {
			paymentdata = payimpl.getPayDeatilsById(transactionid);
			if (paymentdata.equals(null)) {
				header.add("message", "Record not found");
				return new ResponseEntity<>(paymentdata, header, HttpStatus.BAD_REQUEST);
			} else {
				header.add("message", "Record Fetched Successful");
				return new ResponseEntity<>(paymentdata, header, HttpStatus.OK);
			}
		}
		header.add("message", "User not loged in ");
		return new ResponseEntity<>(paymentdata, header, HttpStatus.OK);
	}

	@PostMapping("/order/payment/refund")
	public ResponseEntity<Map<String, String>> getRefund(@RequestBody String data,
			@RequestHeader(value = "authToken", defaultValue = "") String authToken) throws JSONException {
		//Response res = new Response();
		HttpHeaders header =new HttpHeaders();
		Map<String, String> refunddata = new HashMap<String, String>();
		if (payimpl.authenticate(authToken)) {
			Refund refund = new Refund();
			JSONObject refdata = null;
			refdata = new JSONObject(data);
			
			refund.setTransactionid(refdata.getLong("transactionid"));
			refund.setBankname(refdata.getString("bankname"));
			refund.setAccountno(refdata.getString("accountno"));
			refund.setIfsc(refdata.getString("ifsc"));
			refund.setPayableamount(refdata.getString("payableamount"));

			int refres = payimpl.getRefund(refund);
			if (refres != 1) {
				//res.setMessage("Refund Unsuccessfully");
				//res.setStatus(400);
				refunddata.put("transactionid", refdata.getLong("transactionid") + "");
				refunddata.put("refundstatus", "terminated");
				//res.setData(refunddata);
				return new ResponseEntity<>(refunddata, header, HttpStatus.BAD_REQUEST);

			} else {
				payimpl.updateRefundStatus(refdata.getLong("transactionid"));
				//res.setMessage("Refund Delivered Successfully");
				//res.setStatus(200);
				refunddata.put("transactionid", refdata.getLong("transactionid") + "");
				refunddata.put("refundstatus", "Done");
				//res.setData(refunddata);
				header.add("message", "Refund Delivered Successfully");
				return new ResponseEntity<>(refunddata, header, HttpStatus.OK);

			}
		}
		//res.setMessage("User Not Logged In");
		//res.setStatus(401);
		header.add("message", "User not loged in");
		return new ResponseEntity<>(refunddata,header,HttpStatus.UNAUTHORIZED);
	}

}
