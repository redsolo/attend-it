package com.squeed.attendit.api;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.squeed.attendit.db.mock.DbMock;


@Remote(RegistrationService.class)
@Stateless
public class RegistrationServiceBean implements RegistrationService {

	@Override
	public void register(String emailAddress) {
		// TODO Auto-generated method stub

	}

	@Override
	public void register(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregister(String emailAddress) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregister(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Long id, PersonDTO person) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AttendantDTO> getAttendants(Long eventId) {
		return DbMock.getAttendants();
	}

}
