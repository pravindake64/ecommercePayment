package com.globant.ecommerce.paymentdao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globant.ecommerce.paymentmodel.Payment;
import com.globant.ecommerce.paymentmodel.Refund;

/**
 * 
 * @author pravin.dake
 *
 */
public interface PaymentDAO {
	/**
	 * This method is used to make payment 
	 * @param payment
	 * @return
	 */
	public int doPayment(Payment payment);
	
	/**
	 * This method is used to fetch the payment details by its transactionid.
	 */
	public Payment fetchPayDeatilsById(long transactionid );
	/**
	 * this method is used to make the refund 
	 * @param refund
	 * @return
	 */
	public int doRefund(Refund refund);
	/**
	 * this method is used to update payment status wether it is completed or terminated
	 * @param transactionid
	 * @return
	 */
	public int modifyPaymentStatus(long transactionid);
	
	/**
	 * this method is used to update payment status wether it is completed or terminated
	 * @param transactionid
	 * @return
	 */
	public int modifyRefundStatus(long transactionid);
	
	
}
