package com.squeed.attendit.dao.registration;

import java.util.List;

import javax.ejb.Local;

import com.squeed.attendit.db.entity.EventInstance;
import com.squeed.attendit.db.entity.Person;
import com.squeed.attendit.db.entity.Registration;

@Local
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
