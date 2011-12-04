package com.squeed.attendit.db.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Encapsulates a single registration for a person to an event.
 * 
 * @author Erik
 *
 */
@Entity
@Table(name="registration")
public class Registration {

	@GeneratedValue
	private Long id;
	
	/**
	 * The Event instance this registration is all about...
	 */
	@ManyToOne
	private EventInstance event;
	
	@ManyToOne
	private Person person;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Calendar registrationDate;
	
	@ManyToOne
	private RegistrationStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventInstance getEvent() {
		return event;
	}

	public void setEvent(EventInstance event) {
		this.event = event;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Calendar getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Calendar registrationDate) {
		this.registrationDate = registrationDate;
	}

	public RegistrationStatus getStatus() {
		return status;
	}

	public void setStatus(RegistrationStatus status) {
		this.status = status;
	}
	
	
}
