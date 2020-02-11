package com.rabi.swagger.model;

public class ValidationRequest {
	
	private long transactionId;
	private String businessDate;
	private String wType;

	public ValidationRequest() {
		
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getwType() {
		return wType;
	}

	public void setwType(String wType) {
		this.wType = wType;
	}

	
	
}
