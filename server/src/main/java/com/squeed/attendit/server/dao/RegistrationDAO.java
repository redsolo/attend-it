package com.squeed.attendit.server.dao;

import java.util.List;

import com.squeed.attendit.server.model.EventInstance;
import com.squeed.attendit.server.model.Person;
import com.squeed.attendit.server.model.Registration;

public interface RegistrationDAO {
	
	public EventInstance getEvent(Long eventId);
	
	public List<EventInstance> getEvents();
	
	public List<Registration> getRegistrations(Long eventId);
	
	public Registration getRegistration(Long registrationId);
	
	public void updateRegistration(Registration registration);
	
	public Registration createRegistration(Registration registration);

	public Person getPerson(Long personId);
	
	public void updatePerson(Person person);
	
	public Person createPerson(Person person);
}
