package com.squeed.attendit.server.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.squeed.attendit.api.PersonDTO;

/**
 * Contains data about a person.
 * 
 * @author Erik
 *
 */
@Entity
public class Person {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String email;
	private String phone;
	private String company;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Calendar createdDate;
	
	@OneToMany
	private List<Note> notes = new ArrayList<Note>();
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<ExternalIdentifier> externalIdentifiers = new ArrayList<ExternalIdentifier>();
	
	/**
	 * Used to map the events a person wants invites to.
	 */
	@ManyToMany
	private List<Event> eventSubscriptions = new ArrayList<Event>();
	
	/**
	 * Actual event registrations.
	 */
	@OneToMany
	private List<Registration> registrations = new ArrayList<Registration>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<Event> getEventSubscriptions() {
		return eventSubscriptions;
	}

	public void setEventSubscriptions(List<Event> eventSubscriptions) {
		this.eventSubscriptions = eventSubscriptions;
	}

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<ExternalIdentifier> getExternalIdentifiers() {
		return externalIdentifiers;
	}

	public void setExternalIdentifiers(List<ExternalIdentifier> externalIdentifiers) {
		this.externalIdentifiers = externalIdentifiers;
	}

	public PersonDTO toDto() {		
		PersonDTO dto = new PersonDTO();
		dto.setId(this.id);
		dto.setCompany(this.company);
		dto.setEmailAddress(this.email);
		dto.setMobilePhoneNr(this.phone);
		dto.setName(this.name);
		
		// FIXME: Ugly hack, sets the first saved external identifier, if present... format is [vendor]:[identifier], eg. gravatar:user.name
		dto.setUser(this.getExternalIdentifiers().size() > 0 ? this.getExternalIdentifiers().get(0).getExternalIdService().getName() + ":" + this.getExternalIdentifiers().get(0).getValue() : null);
		return dto;
	}
	
	public void mergeValuesFrom(PersonDTO person) {
		this.setEmail(person.getEmailAddress());
		this.setName(person.getName());
		this.setCompany(person.getCompany());
		this.setPhone(person.getMobilePhoneNr());
		
		// FIXME something to set user (external identifier) properly...
	}
}
