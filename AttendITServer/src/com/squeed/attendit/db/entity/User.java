package com.squeed.attendit.db.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Users can create events
 * 
 * @author Erik
 *
 */
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;	
	
	private String email;
	
	private String password;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar createdDate;
	
	@ManyToMany
	private List<EventInstance> myEventInstances = new ArrayList<EventInstance>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public List<EventInstance> getMyEvents() {
		return myEventInstances;
	}

	public void setMyEvents(List<EventInstance> myEvents) {
		this.myEventInstances = myEvents;
	}
	
	
}
