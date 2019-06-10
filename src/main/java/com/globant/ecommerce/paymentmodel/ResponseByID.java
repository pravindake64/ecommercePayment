package com.globant.ecommerce.paymentmodel;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class ResponseByID {
	
	private String Message;
	private Payment data;
	private int status;
	
	
	public ResponseByID() {
		// TODO Auto-generated constructor stub
	}


	public ResponseByID(String message, Payment data, int status) {
		super();
		Message = message;
		this.data = data;
		this.status = status;
	}


	public Payment getData() {
		return data;
	}


	public void setData(Payment data) {
		this.data = data;
	}


	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	

}
