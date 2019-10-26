package com.rabi.swagger.model;

public class CourierResponse {
	String randomId;
	String process;
	String date;
	
	public CourierResponse() {}
	public CourierResponse(String randomId, String process, String date) {
		super();
		this.randomId = randomId;
		this.process = process;
		this.date = date;
	}
	public String getRandomId() {
		return randomId;
	}
	public void setRandomId(String randomId) {
		this.randomId = randomId;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "CourierResponse [randomId=" + randomId + ", process=" + process + ", date=" + date + "]";
	}
	
	

}
