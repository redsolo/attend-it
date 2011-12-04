package com.squeed.attendit.api;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.squeed.attendit.dao.registration.RegistrationDAO;
import com.squeed.attendit.dao.registration.RegistrationDAOImpl;
import com.squeed.attendit.db.entity.Person;
import com.squeed.attendit.db.entity.Registration;
import com.squeed.attendit.db.entity.RegistrationStatus;


@Remote(RegistrationService.class)
@Stateless
public class RegistrationServiceBean implements RegistrationService {
	
	private RegistrationDAO dao;
	
	@PostConstruct
	public void init() {
		//dao = new DbMock();
		dao = new RegistrationDAOImpl();
	}

	
	@Override
	public void register(Long registrationId) {
		Registration registration = dao.getRegistration(registrationId);
		registration.setStatus(RegistrationStatus.ARRIVED);
		dao.updateRegistration(registration);
	}

	@Override
	public void unregister(Long registrationId) {
		Registration registration = dao.getRegistration(registrationId);
		registration.setStatus(RegistrationStatus.REGISTERED);
		dao.updateRegistration(registration);
	}

	@Override
	public void update(Long personId, PersonDTO person) {
		Person dbPerson = dao.getPerson(personId);
		dbPerson.mergeValuesFrom(person);
		dao.updatePerson(dbPerson);
	}

	@Override
	public List<RegistrationDTO> getRegistrations(Long eventId) {
		List<Registration> registrations = dao.getRegistrations(eventId);
		return Registration.toDtoList(registrations);
	}
}
