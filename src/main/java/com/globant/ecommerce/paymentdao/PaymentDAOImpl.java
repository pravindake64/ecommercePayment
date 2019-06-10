package com.globant.ecommerce.paymentdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.globant.ecommerce.paymentmodel.Payment;
import com.globant.ecommerce.paymentmodel.Refund;

@Repository("paymentDAO")
public class PaymentDAOImpl implements PaymentDAO {
	@Autowired
	private JdbcTemplate jdbctemplate;

	/*
	 * method to insert payment details, "paymentmode" are as follows,these flages
	 * are based on the first letter of paymentmode e.g Debit card i.e D=4 Debit
	 * card:4,Credit card:3,Net Banking:14,UPI:21,Cash On Delivery:1.
	 * 
	 * for "cod" 1/null, i.e 1 if payment is via Cod otherwise null
	 * 
	 * for "paymentstatus" 1/null, i.e 1 if payment is via Cod otherwise null
	 * 
	 * for "refund" 1/null, i.e 1 if getRefund is hit by user then value will be
	 * updated oterwise it will be null.
	 */
	@Override
	public int doPayment(Payment payment) {

		final String sql = "insert into payment(orderid,userid,paymentmode,payableamount,cardno,cod,cvv,expdate,password,paymentstatus,upi,username,bankname,transactionid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object param[] = { payment.getOrderid(), payment.getUserid(), payment.getPaymentmode(),
				payment.getPayableamount(), payment.getCardno(), payment.getCod(), payment.getCvv(),
				payment.getExpdate(), payment.getPassword(), payment.getPaymentstatus(), payment.getUpi(),
				payment.getUsername(), payment.getBankname(), payment.getTransactionid() };
		int res = jdbctemplate.update(sql, param);

		return res;
	}

	// method for fetching a details of perticular transaction via "transactionid"
	@Override
	public Payment fetchPayDeatilsById(long transactionid) {
		final String sql = "select * from payment where transactionid=?";
		Object param[] = { transactionid };
		Payment payment = jdbctemplate.queryForObject(sql, param, new BeanPropertyRowMapper<Payment>(Payment.class));
		return payment;
	}

	// if the transaction is successfull this method get called
	@Override
	public int modifyPaymentStatus(long transactionid) {
		final String sql = "update payment set paymentstatus=1 where transactionid=? ";
		Object param = transactionid;
		int res = jdbctemplate.update(sql, param);
		return res;
	}

	/*
	 * this method does two jobs 1. Update the "refund" flag in payment table.
	 * 
	 * 2. insert the bank details,amount to be refund.
	 * 
	 * this method will only get executed successfully,if the transactionid send by
	 * user and transactionid present in database matches.
	 * 
	 */
	@Override
	public int doRefund(Refund refund) {

		Payment transactiondetails = fetchPayDeatilsById(refund.getTransactionid());
		if (refund.getTransactionid() == transactiondetails.getTransactionid()) {

			final String sql = "update payment set refund=1 where transactionid=? ";
			Object param[] = { refund.getTransactionid() };
			int res = jdbctemplate.update(sql, param);

			final String query = "insert into refund(transactionid,bankname,accountno,ifsc,payableamount) values(?,?,?,?,?)";
			Object refparam[] = { refund.getTransactionid(), refund.getBankname(), refund.getAccountno(),
					refund.getIfsc(), transactiondetails.getPayableamount() };
			jdbctemplate.update(query, refparam);
			return res;
		}
		return 1;
	}

	// if the getrefund is successfully executed,then only this method get called
	@Override
	public int modifyRefundStatus(long transactionid) {
		final String sql = "update refund set refundstatus=1 where transactionid=? ";
		Object param = transactionid;
		int res = jdbctemplate.update(sql, param);
		return res;
	}

	

}
