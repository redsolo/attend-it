package com.squeed.attendit.api;

import java.io.Serializable;

public class AttendantDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private PersonDTO person;
	private EventDTO event;
	
	private int status = 0; // 0 = not arrived, 1 = arrived;
	
	public AttendantDTO() {}
	
	

	public AttendantDTO(Long id, PersonDTO person, EventDTO event, int status) {
		super();
		this.id = id;
		this.person = person;
		this.event = event;
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

	public EventDTO getEvent() {
		return event;
	}

	public void setEvent(EventDTO event) {
		this.event = event;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
	
}
