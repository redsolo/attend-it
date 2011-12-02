package com.squeed.attendit.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("registration")
@Produces("application/json")
@Consumes("application/json")
public interface RegistrationService {
	
	@POST
	@Path("/register/{email}")
	public void register(@PathParam("email") String emailAddress);
	
	@POST
	@Path("/register/attendant/{id}")
	public void register(Long id);
	
	@POST
	@Path("/unregister/{email}")
	public void unregister(String emailAddress);

	@POST
	@Path("/register/attendant/{id}")
	public void unregister(Long id);
	
	@PUT
	@Path("/person/{id}")
	public void update(@PathParam("id") Long id, PersonDTO person);
	
	@Path("/event/{eventId}/attendants")
    @GET
	public List<AttendantDTO> getAttendants(@PathParam("eventId") Long eventId);
	
}
