package com.globant.ecommerce.paymentmodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Payment {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int payid;
	private int orderid;
	private int userid;
	private int paymentmode;
	private double payableamount;
	private String cardno;
	private String expdate;
	private String cvv;
	private String upi;
	private String bankname;
	private String username;
	private String password;
	private int cod;
	private String paymentstatus;
	private long transactionid;

	public Payment() {
		// TODO Auto-generated constructor stub
	}

	public Payment(int payid, int orderid, int userid, int paymentmode, double payableamount, String cardno,
			String expdate, String cvv, String upi, String bankname, String username, String password, int cod,
			String paymentstatus, long transactionid) {
		super();
		this.payid = payid;
		this.orderid = orderid;
		this.userid = userid;
		this.paymentmode = paymentmode;
		this.payableamount = payableamount;
		this.cardno = cardno;
		this.expdate = expdate;
		this.cvv = cvv;
		this.upi = upi;
		this.bankname = bankname;
		this.username = username;
		this.password = password;
		this.cod = cod;
		this.paymentstatus = paymentstatus;
		this.transactionid = transactionid;
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

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getExpdate() {
		return expdate;
	}

	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getUpi() {
		return upi;
	}

	public void setUpi(String upi) {
		this.upi = upi;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public int getPayid() {
		return payid;
	}

	public void setPayid(int payid) {
		this.payid = payid;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getPaymentmode() {
		return paymentmode;
	}

	public void setPaymentmode(int paymentmode) {
		this.paymentmode = paymentmode;
	}

	public double getPayableamount() {
		return payableamount;
	}

	public void setPayableamount(double payableamount) {
		this.payableamount = payableamount;
	}

	

}
