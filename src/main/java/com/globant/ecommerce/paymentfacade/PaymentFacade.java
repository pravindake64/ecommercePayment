package com.globant.ecommerce.paymentfacade;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.globant.ecommerce.paymentmodel.Payment;
import com.globant.ecommerce.paymentmodel.Refund;
/**
 * 
 * @author pravin.dake
 *
 */
public interface PaymentFacade {
	/**
	 * this method is used to make the payment
	 * @param payment
	 * @return
	 */
	public int makePayment(Payment payment);
	
	/**
	 * this method is used to get payment details by transaction id.
	 * @param transactionid
	 * @return
	 */
	public Payment getPayDeatilsById(long transactionid );
	/**
	 * this method is used to make the refund.
	 * @param refund
	 * @return
	 */
	public int getRefund(Refund refund);
	/**
	 * this method is used to update the payment status.
	 * @param transactionid
	 * @return
	 */
	public int updatePaymentStatus(long transactionid);
	/**
	 * this method is used to update refund status.
	 * @param transactionid
	 * @return
	 */
	public int updateRefundStatus(long transactionid);
	/**
	 * this method is used to authenticate the user.
	 * @param authToken
	 * @return
	 */
	public boolean authenticate(String authToken);
	
	
}
