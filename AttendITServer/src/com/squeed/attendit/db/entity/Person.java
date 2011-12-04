package com.squeed.attendit.db.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Contains data about a person.
 * 
 * @author Erik
 *
 */
@Entity
public class Person {
	
	@GeneratedValue
	private Long id;
	
	private String name;
	private String email;
	private String phone;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Calendar createdDate;
	
	@OneToMany
	private List<Note> notes = new ArrayList<Note>();
	
	@OneToMany
	private List<ExternalIdentifier> externalIdentifiers = new ArrayList<ExternalIdentifier>();
	
	/**
	 * Used to map the events a person wants invites to.
	 */
	@OneToMany
	private List<EventInstance> eventSubscriptions = new ArrayList<EventInstance>();
	
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
	
	
	
}
