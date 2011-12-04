package com.squeed.attendit.db.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="event_instance")
public class EventInstance {

	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	private String description;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Calendar eventDateTime;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Calendar lastRegistrationDate;
	
	private Integer maxAttendants;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Calendar createdDate;
	
	@ManyToOne
	private User createdBy;
	
	@ManyToMany
	private List<User> administrators = new ArrayList<User>();
	
	@OneToMany
	private List<Registration> registrations = new ArrayList<Registration>();
	
	@ManyToOne
	private Event event;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Calendar getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(Calendar eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public Calendar getLastRegistrationDate() {
		return lastRegistrationDate;
	}

	public void setLastRegistrationDate(Calendar lastRegistrationDate) {
		this.lastRegistrationDate = lastRegistrationDate;
	}

	public Integer getMaxAttendants() {
		return maxAttendants;
	}

	public void setMaxAttendants(Integer maxAttendants) {
		this.maxAttendants = maxAttendants;
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public List<User> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(List<User> administrators) {
		this.administrators = administrators;
	}

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
