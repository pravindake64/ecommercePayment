package com.globant.ecommerce.paymentmodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
/**
 * 
 * @author pravin.dake
 *
 */
@Entity
public class Refund {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long transactionid;
	private String bankname;
	private String accountno;
	private String ifsc;
	private String payableamount;
	private String refundstatus;
	
	public Refund() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Refund(int id, long transactionid, String bankname, String accountno, String ifsc, String payableamount,
			String refundstatus) {
		super();
		this.id = id;
		this.transactionid = transactionid;
		this.bankname = bankname;
		this.accountno = accountno;
		this.ifsc = ifsc;
		this.payableamount = payableamount;
		this.refundstatus = refundstatus;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(long transactionid) {
		this.transactionid = transactionid;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getAccountno() {
		return accountno;
	}

	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getPayableamount() {
		return payableamount;
	}

	public void setPayableamount(String payableamount) {
		this.payableamount = payableamount;
	}

	public String getRefundstatus() {
		return refundstatus;
	}

	public void setRefundstatus(String refundstatus) {
		this.refundstatus = refundstatus;
	}
	
	
	

}
