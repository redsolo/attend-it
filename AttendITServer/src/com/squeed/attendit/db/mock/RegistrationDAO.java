package com.squeed.attendit.db.mock;

import java.util.List;

import com.squeed.attendit.api.AttendantDTO;
import com.squeed.attendit.api.PersonDTO;

public interface RegistrationDAO {
	public List<AttendantDTO> getAttendants();

	public void register(Long id);

	public void register(String emailAddress);
	
	public void unregister(Long id);

	public void unregister(String emailAddress);

	public void update(Long id, PersonDTO person);
}
