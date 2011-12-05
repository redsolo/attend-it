package com.squeed.attendit.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Simple entity to add a note to a person.
 * 
 * @author Erik
 *
 */
@Entity
public class Note {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String text;
	
	@ManyToOne
	private Person person;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
