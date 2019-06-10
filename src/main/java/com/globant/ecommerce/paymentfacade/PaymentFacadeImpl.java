package com.globant.ecommerce.paymentfacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.globant.ecommerce.paymentmodel.Payment;
import com.globant.ecommerce.paymentmodel.Refund;
import com.globant.ecommerce.paymentservice.PaymentService;
import com.globant.ecommerce.paymentservice.PaymentServiceImpl;

@Repository("paymentFacade")
public class PaymentFacadeImpl implements PaymentFacade {
	@Autowired
	private PaymentServiceImpl payservice;

	@Override
	public int makePayment(Payment payment) {
		// TODO Auto-generated method stub
		return payservice.makepayment(payment) ;
	}

	@Override
	public Payment getPayDeatilsById(long transactionid) {
		// TODO Auto-generated method stub
		return payservice.getPayDeatilsById(transactionid);
	}

	@Override
	public int getRefund(Refund refund) {
		// TODO Auto-generated method stub
		return payservice.getRefund(refund);
	}

	@Override
	public int updatePaymentStatus(long transactionid) {
		// TODO Auto-generated method stub
		return payservice.updatePaymentstatus(transactionid);
	}

	@Override
	public int updateRefundStatus(long transactionid) {
		// TODO Auto-generated method stub
		return payservice.updateRefundStatus(transactionid);
	}

	@Override
	public boolean authenticate(String authToken) {
		// TODO Auto-generated method stub
		return payservice.authenticate(authToken);
	}
	

}
