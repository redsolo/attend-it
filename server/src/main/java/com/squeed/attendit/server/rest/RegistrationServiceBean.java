package com.squeed.attendit.server.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.squeed.attendit.api.PersonDTO;
import com.squeed.attendit.api.RegistrationDTO;
import com.squeed.attendit.server.dao.RegistrationDAO;
import com.squeed.attendit.server.dao.RegistrationDAOImpl;
import com.squeed.attendit.server.model.Person;
import com.squeed.attendit.server.model.Registration;
import com.squeed.attendit.server.model.RegistrationStatus;


//@Stateful
//@RequestScoped
@Path("registration")
//@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
public class RegistrationServiceBean {
	
	private Logger log = Logger.getLogger(RegistrationServiceBean.class);
	
	@Inject
	private RegistrationDAO dao;
	

	@POST
	@Path("/register/attendant/{id}")
	public void register(@PathParam("id") Long registrationId) {
		Registration registration = dao.getRegistration(registrationId);
		registration.setStatus(RegistrationStatus.ARRIVED);
		dao.updateRegistration(registration);
	}

	@POST
	@Path("/unregister/attendant/{id}")
	public void unregister(@PathParam("id") Long registrationId) {
		Registration registration = dao.getRegistration(registrationId);
		registration.setStatus(RegistrationStatus.REGISTERED);
		dao.updateRegistration(registration);
	}

	@POST
	@Path("/person/{id}")
	public void update(@PathParam("id") Long personId, PersonDTO person) {
		Person dbPerson = dao.getPerson(personId);
		dbPerson.mergeValuesFrom(person);
		dao.updatePerson(dbPerson);
	}

	
	@Path("/event/{eventId}/attendants")
    @GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
	public List<RegistrationDTO> getRegistrations(@PathParam("eventId") Long eventId) {
		log.info("ENTER - getRegistrations(" + eventId + ")");
		List<Registration> registrations = dao.getRegistrations(eventId);
		return Registration.toDtoList(registrations);
	}
}
