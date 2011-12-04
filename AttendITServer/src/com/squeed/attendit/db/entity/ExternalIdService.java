package com.squeed.attendit.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Identifies an external identification service such as Twitter, Facebook, Gravatar or Google-ID.
 * 
 * Should be populated by init script.
 * 
 * @author Erik
 *
 */
@Entity
@Table(name="ext_id_service")
public class ExternalIdService {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String serviceUrl;

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

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	
	
	
}
