package com.squeed.attendit.db.entity.util;

import com.squeed.attendit.api.PersonDTO;
import com.squeed.attendit.db.entity.Person;

/**
 * @deprecated not used yet...
 * @author Erik
 *
 */
public class EntityHelper {
	
	
	
	public Person fromDto(PersonDTO person) {
		Person dbPerson = new Person();
		dbPerson.setEmail(person.getEmailAddress());
		dbPerson.setName(person.getName());
		dbPerson.setCompany(person.getCompany());
		dbPerson.setPhone(person.getMobilePhoneNr());
		
		return dbPerson;
	}

	
}
