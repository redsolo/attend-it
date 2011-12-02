package com.squeed.attendit.api;

import java.util.List;

public interface RegistrationService {
	public void register(String emailAddress);
	public void register(Long id);
	public void unregister(String emailAddress);
	public void unregister(Long id);
	
	public void update(Long id, PersonDTO person);
	
	public List<AttendantDTO> getAttendants(Long eventId);
	
}
