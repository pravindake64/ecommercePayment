package com.globant.ecommerce.paymentdao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.globant.ecommerce.paymentmodel.Payment;
import com.globant.ecommerce.paymentmodel.Refund;


public interface PaymentDAO {

	public int doPayment(Payment payment);
	
	public Payment fetchPayDeatilsById(long transactionid );
	
	public int doRefund(Refund refund);
	
	public int modifyPaymentStatus(long transactionid);
	
	public int modifyRefundStatus(long transactionid);
	
	
}
