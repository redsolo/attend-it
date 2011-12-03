package com.squeed.attendit.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("registration")
//@Produces("application/json")
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//@Consumes("application/json")
@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
public interface RegistrationService {
	
	@POST
	@Path("/register/{email}")
	public void register(@PathParam("email") String emailAddress);
	
	@POST
	@Path("/register/attendant/{id}")
	public void register(@PathParam("id") Long id);
	
	@POST
	@Path("/unregister/{email}")
	public void unregister(@PathParam("email") String emailAddress);

	@POST
	@Path("/unregister/attendant/{id}")
	public void unregister(Long id);
	
	@POST
	@Path("/person/{id}")
	public void update(@PathParam("id") Long id, PersonDTO person);
	
	@Path("/event/{eventId}/attendants")
    @GET
	public List<AttendantDTO> getAttendants(@PathParam("eventId") Long eventId);
	
}
