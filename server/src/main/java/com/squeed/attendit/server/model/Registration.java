package com.squeed.attendit.server.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.squeed.attendit.api.RegistrationDTO;

/**
 * Encapsulates a single registration for a person to an event.
 * 
 * @author Erik
 *
 */
@Entity
@Table(name="registration")
public class Registration {

	@Id
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
	
	@Enumerated(EnumType.STRING)
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

	public static List<RegistrationDTO> toDtoList(List<Registration> registrations) {
		List<RegistrationDTO> list = new ArrayList<RegistrationDTO>();
		for(Registration reg : registrations) {
			list.add(reg.toDto());
		}
		
		return list;
	}

	public RegistrationDTO toDto() {
		RegistrationDTO dto = new RegistrationDTO();
		dto.setId(this.id);
		dto.setEventId(this.getEvent().getId());
		dto.setPerson(this.getPerson().toDto());
		dto.setStatus(this.getStatus().getStatusCode());
		return dto;
	}
}
