package com.squeed.attendit.server.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.squeed.attendit.server.model.EventInstance;
import com.squeed.attendit.server.model.Person;
import com.squeed.attendit.server.model.Registration;

@Stateless
@Local(RegistrationDAO.class)
public class RegistrationDAOImpl implements RegistrationDAO {
	
	@Inject
	private EntityManager em;
	
	@Override
	public List<EventInstance> getEvents() {
		List<EventInstance> resultList = em.createQuery("select e from EventInstance e").getResultList();
		for(EventInstance ei : resultList) {
			ei.getAdministrators().size();
			ei.getRegistrations().size();
			
		}
		return resultList;
	}

	@Override
	public List<Registration> getRegistrations(Long eventId) {
		
		System.out.println("ENTER - getRegistrations. Do we have an entityManager?" + (em != null));
		Query q = em.createQuery("select r from Registration r where r.event.id=:eventId");
		System.out.println("Created query");
		q.setParameter("eventId", eventId);
		System.out.println("Set param");
		List<Registration> l = q.getResultList();
		System.out.println("Got resultList");
		return l;
	}

	@Override
	public Registration createRegistration(Registration registration) {
		return em.merge(registration);
	}

	@Override
	public void updateRegistration(Registration registration) {
		em.merge(registration);
	}

	@Override
	public EventInstance getEvent(Long eventId) {
		return em.find(EventInstance.class, eventId);
	}

	@Override
	public Registration getRegistration(Long registrationId) {
		return em.find(Registration.class, registrationId);
	}

	@Override
	public Person getPerson(Long personId) {
		return em.find(Person.class, personId);
	}

	@Override
	public void updatePerson(Person person) {
		em.merge(person);
	}

	@Override
	public Person createPerson(Person person) {
		return em.merge(person);
	}

}
