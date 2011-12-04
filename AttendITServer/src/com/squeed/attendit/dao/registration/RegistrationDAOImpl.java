package com.squeed.attendit.dao.registration;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.squeed.attendit.db.entity.EventInstance;
import com.squeed.attendit.db.entity.Person;
import com.squeed.attendit.db.entity.Registration;


@SuppressWarnings("unchecked")
@Stateless
public class RegistrationDAOImpl implements RegistrationDAO {
	
	EntityManager em;
	
	@PostConstruct
	public void init() {
		if(em == null) {
			System.err.println("EntityManager was null, could not inject?");
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("attendit");			
			em = emf.createEntityManager();			
		}
	}
	
	@Override
	public List<EventInstance> getEvents() {
		return em.createQuery("select e from EventInstance e").getResultList();
	}

	@Override
	public List<Registration> getRegistrations(Long eventId) {
		if(em == null) {
			System.err.println("EntityManager was null, could not inject?");
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("attendit");			
			em = emf.createEntityManager();			
		}
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
