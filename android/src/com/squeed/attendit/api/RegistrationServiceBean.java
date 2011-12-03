package com.squeed.attendit.api;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.squeed.attendit.db.mock.DbMock;
import com.squeed.attendit.db.mock.RegistrationDAO;


@Remote(RegistrationService.class)
@Stateless
public class RegistrationServiceBean implements RegistrationService {
	
	private RegistrationDAO dao;
	
	@PostConstruct
	public void init() {
		dao = new DbMock();
	}

	@Override
	public void register(String emailAddress) {
		dao.register(emailAddress);
	}

	@Override
	public void register(Long id) {
		dao.register(id);
	}

	@Override
	public void unregister(String emailAddress) {
		dao.unregister(emailAddress);
	}

	@Override
	public void unregister(Long id) {
		dao.unregister(id);
	}

	@Override
	public void update(Long id, PersonDTO person) {
		dao.update(id, person);
	}

	@Override
	public List<AttendantDTO> getAttendants(Long eventId) {
		return dao.getAttendants();
	}

}
