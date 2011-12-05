package com.squeed.attendit.api;

import java.io.Serializable;

public class RegistrationDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private PersonDTO person;
	private Long eventId;
	private int status = 0; // 0 = not arrived, 1 = arrived;
	
	public RegistrationDTO() {}
	
	

	public RegistrationDTO(Long id, PersonDTO person, Long eventId, int status) {
		super();
		this.id = id;
		this.person = person;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}

	public Long getEventId() {
		return eventId;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}



	public void setEventId(Long id2) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
