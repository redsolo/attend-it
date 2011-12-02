package com.squeed.attendit.api;

import java.io.Serializable;

public class PersonDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String emailAddress;
	private String name;
	private String company;
	private String user;
	private String mobilePhoneNr;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmailAddress() {
		return getUser() + "@squeed.com";
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getName() {
		return name;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getMobilePhoneNr() {
		return mobilePhoneNr;
	}
	public void setMobilePhoneNr(String mobilePhoneNr) {
		this.mobilePhoneNr = mobilePhoneNr;
	}
	
	
}
