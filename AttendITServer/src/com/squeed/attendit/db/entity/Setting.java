package com.squeed.attendit.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Setting {
	
	public static final String CORE_DATA_INITIALIZED = "core.data.initialized";
	public static final String DEMO_DATA_INITIALIZED = "demo.data.initialized";

	
	@Id
	@GeneratedValue
	private Long id;
	
	private String key;
	
	private String value;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
