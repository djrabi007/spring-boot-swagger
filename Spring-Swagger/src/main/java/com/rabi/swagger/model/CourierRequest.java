package com.rabi.swagger.model;

public class CourierRequest {
	String source;
	String consignmentName;
	int weight;
	String senderName;
	String state;
	String pin;
	String contact;
	
	
	public CourierRequest() {}
	public CourierRequest(String source, String consignmentName, int weight, String senderName, String state,
			String pin, String contact) {
		super();
		this.source = source;
		this.consignmentName = consignmentName;
		this.weight = weight;
		this.senderName = senderName;
		this.state = state;
		this.pin = pin;
		this.contact = contact;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getConsignmentName() {
		return consignmentName;
	}
	public void setConsignmentName(String consignmentName) {
		this.consignmentName = consignmentName;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "CourierRequest [source=" + source + ", consignmentName=" + consignmentName + ", weight=" + weight
				+ ", senderName=" + senderName + ", state=" + state + ", pin=" + pin + ", contact=" + contact + "]";
	}
	

}
