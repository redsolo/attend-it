package com.squeed.attendit.api;

import java.io.Serializable;
import java.util.Calendar;

public class EventDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private String eventHost;
	private String place;
	private Calendar dateOfEvent;
	
	
	public EventDTO() {}
	
	public EventDTO(Long id, String title, String eventHost, String place,
			Calendar dateOfEvent) {
		super();
		this.id = id;
		this.title = title;
		this.eventHost = eventHost;
		this.place = place;
		this.dateOfEvent = dateOfEvent;
	}
	
	
	
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
	public String getEventHost() {
		return eventHost;
	}
	public void setEventHost(String eventHost) {
		this.eventHost = eventHost;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Calendar getDateOfEvent() {
		return dateOfEvent;
	}
	public void setDateOfEvent(Calendar dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}
	
	
}
