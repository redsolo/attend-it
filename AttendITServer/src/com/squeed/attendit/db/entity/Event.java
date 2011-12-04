package com.squeed.attendit.db.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Since many events are reoccuring, this identifies a event "group" persons can subscribe to.
 * 
 * E.g. "JavaForum" or "nForum" are events, while "JavaForum Q2 2012" or "nForum Windows 8 special" are EventInstance(s).
 * @author Erik
 *
 */
@Entity
public class Event {
	
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String description;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	private Calendar createdDate;
	
	@ManyToOne
	private User createdBy;
	
	@OneToMany
	private List<EventInstance> eventInstances = new ArrayList<EventInstance>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<EventInstance> getEventInstances() {
		return eventInstances;
	}

	public void setEventInstances(List<EventInstance> eventInstances) {
		this.eventInstances = eventInstances;
	}
	
	
}
