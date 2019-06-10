package com.globant.ecommerce.paymentfacade;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.globant.ecommerce.paymentmodel.Payment;
import com.globant.ecommerce.paymentmodel.Refund;

public interface PaymentFacade {

	public int makePayment(Payment payment);
	
	public Payment getPayDeatilsById(long transactionid );
	
	public int getRefund(Refund refund);
	
	public int updatePaymentStatus(long transactionid);
	
	public int updateRefundStatus(long transactionid);
	
	public boolean authenticate(String authToken);
	
	
}
