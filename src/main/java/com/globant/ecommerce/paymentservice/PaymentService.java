package com.globant.ecommerce.paymentservice;

import com.globant.ecommerce.paymentmodel.Payment;
import com.globant.ecommerce.paymentmodel.Refund;

public interface PaymentService {
	/**
	 * 
	 * @param payment
	 * @return
	 */
	public int makepayment(Payment payment);
	
	public Payment getPayDeatilsById(long transactionid );
	
	public int getRefund(Refund refund);
	
	public int updatePaymentstatus(long transactionid);
	
	public int updateRefundStatus(long transactionid);
	
	public boolean authenticate(String authToken);
	
}
