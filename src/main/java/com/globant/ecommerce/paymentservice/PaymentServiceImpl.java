package com.globant.ecommerce.paymentservice;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.globant.ecommerce.paymentdao.PaymentDAO;
import com.globant.ecommerce.paymentdao.PaymentDAOImpl;
import com.globant.ecommerce.paymentmodel.Payment;
import com.globant.ecommerce.paymentmodel.Refund;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentDAOImpl paydao;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public int makepayment(Payment payment) {
		// TODO Auto-generated method stub
		return paydao.doPayment(payment);
	}

	@Override
	public Payment getPayDeatilsById(long transactionid) {
		// TODO Auto-generated method stub
		return paydao.fetchPayDeatilsById(transactionid);
	}

	@Override
	public int getRefund(Refund refund) {
		// TODO Auto-generated method stub
		return paydao.doRefund(refund);
	}

	@Override
	public int updatePaymentstatus(long transactionid) {
		// TODO Auto-generated method stub
		return paydao.modifyPaymentStatus(transactionid);
	}

	@Override
	public int updateRefundStatus(long transactionid) {
		// TODO Auto-generated method stub
		return paydao.modifyRefundStatus(transactionid);
	}

	@Override
	public boolean authenticate(String authToken) {
		if (true)
			return true;
		String url = "http://192.168.43.163:8080/checklogin";
		HttpHeaders headers = new HttpHeaders();
		headers.set("authToken", authToken);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		JSONObject jo;
		try {
			jo = new JSONObject(resp.getBody());
			String statusCode = jo.getString("statusCode");
			//System.out.println(statusCode);
			if (statusCode.equals("200")) {
				return true;
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return false;
	}

}
