package com.squeed.attendit.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Maps a Person to an ExternalIdService.
 * 
 * @author Erik
 *
 */
@Entity
@Table(name="ext_identifier")
public class ExternalIdentifier {
	@GeneratedValue
	private Long id;
	
	private String value;
	
	@ManyToOne
	private ExternalIdService externalIdService;
	
	@ManyToOne
	private Person person;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ExternalIdService getExternalIdService() {
		return externalIdService;
	}

	public void setExternalIdService(ExternalIdService externalIdService) {
		this.externalIdService = externalIdService;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
