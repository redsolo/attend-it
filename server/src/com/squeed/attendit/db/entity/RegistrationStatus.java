package com.squeed.attendit.db.entity;



public enum RegistrationStatus {
	
	REGISTERED(0), ARRIVED(1), UNREGISTERED(2); 
	
	private RegistrationStatus(int statusCode) {
		this.statusCode = statusCode;
	}
	
	private int statusCode;
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public static RegistrationStatus fromCode(int statusCode) {
		switch(statusCode) {
		case 0:
			return REGISTERED;
		case 1:
			return ARRIVED;
		case 2:
			return UNREGISTERED;
		default:
			return REGISTERED;
		}
	}
}
