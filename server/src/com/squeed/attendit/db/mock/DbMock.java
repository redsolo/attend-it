package com.squeed.attendit.db.mock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.squeed.attendit.api.EventDTO;
import com.squeed.attendit.api.PersonDTO;
import com.squeed.attendit.api.RegistrationDTO;

public class DbMock {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private static String people = "[{\"mobilePhoneNr\": \"0733205177\", \"name\": \"Adam Lith\", \"user\": \"adam.lith\"}, {\"mobilePhoneNr\": \"0722142558\", \"name\": \"Anders Sjöberg\", \"user\": \"anders.sjoberg\"}, {\"mobilePhoneNr\": \"0703449075\", \"name\": \"Andreas Pringle\", \"user\": \"andreas.pringle\"}, {\"mobilePhoneNr\": \"0702757015\", \"name\": \"Daniel Eriksson\", \"user\": \"daniel.eriksson\"}, {\"mobilePhoneNr\": \"0733992721\", \"name\": \"Erik Lupander\", \"user\": \"erik.lupander\"}, {\"mobilePhoneNr\": \"0768800763\", \"name\": \"Erik Ramfelt\", \"user\": \"erik.ramfelt\"}, {\"mobilePhoneNr\": \"0739306403\", \"name\": \"Fredrik Corneliusson\", \"user\": \"fredrik.corneliusson\"}, {\"mobilePhoneNr\": \"0727330054\", \"name\": \"Fredrik Normén\", \"user\": \"fredrik.normen\"}, {\"mobilePhoneNr\": \"0702778511\", \"name\": \"Fredrik Wendt\", \"user\": \"fredrik.wendt\"}, {\"mobilePhoneNr\": \"0702224459\", \"name\": \"Janne Hasslöf\", \"user\": \"janne.hasslof\"}, {\"mobilePhoneNr\": \"0738515210\", \"name\": \"Johan Normén\", \"user\": \"johan.normen\"}, {\"mobilePhoneNr\": \"0738531013\", \"name\": \"John Tjust\", \"user\": \"john.tjust\"}, {\"mobilePhoneNr\": \"0736545987\", \"name\": \"Jonas Enlund\", \"user\": \"jonas.enlund\"}, {\"mobilePhoneNr\": \"0739890278\", \"name\": \"Julia Klingvall\", \"user\": \"julia.klingvall\"}, {\"mobilePhoneNr\": \"0733704722\", \"name\": \"Kristoffer Teuber\", \"user\": \"kristoffer.teuber\"}, {\"mobilePhoneNr\": \"0708156463\", \"name\": \"Linus Norén\", \"user\": \"linus.noren\"}, {\"mobilePhoneNr\": \"0761023854\", \"name\": \"Magnus Härlin\", \"user\": \"magnus.harlin\"}, {\"mobilePhoneNr\": \"0708221771\", \"name\": \"Markus Wahl\", \"user\": \"markus.wahl\"}, {\"mobilePhoneNr\": \"0708430248\", \"name\": \"Martin Jönsson\", \"user\": \"martin.jonsson\"}, {\"mobilePhoneNr\": \"0730282823\", \"name\": \"Martin Woxneryd\", \"user\": \"martin.woxneryd\"}, {\"mobilePhoneNr\": \"0702469109\", \"name\": \"Mats Nilsson\", \"user\": \"mats.nilsson\"}, {\"mobilePhoneNr\": \"0736002767\", \"name\": \"Mattias Sandelin\", \"user\": \"mattias.sandelin\"}, {\"mobilePhoneNr\": \"0702438761\", \"name\": \"Niclas Åstrand\", \"user\": \"niclas.astrand\"}, {\"mobilePhoneNr\": \"0702022133\", \"name\": \"Niklas Johansson\", \"user\": \"niklas.johansson\"}, {\"mobilePhoneNr\": \"0702109203\", \"name\": \"Ola Klasson\", \"user\": \"ola.klasson\"}, {\"mobilePhoneNr\": \"0708618511\", \"name\": \"Peter Lindh\", \"user\": \"peter.lindh\"}, {\"mobilePhoneNr\": \"0703286086\", \"name\": \"Pierre Ingmansson\", \"user\": \"pierre.ingmansson\"}, {\"mobilePhoneNr\": \"0709100528\", \"name\": \"Rikard Thulin\", \"user\": \"rikard.thulin\"}, {\"mobilePhoneNr\": \"0709723864\", \"name\": \"Tomas Trolltoft\", \"user\": \"tomas.trolltoft\"}, {\"mobilePhoneNr\": \"0703333134\", \"name\": \"Torbjörn Karlsson\", \"user\": \"torbjorn.karlsson\"}, {\"mobilePhoneNr\": \"0702751019\", \"name\": \"Wincent Papousek\", \"user\": \"wincent.papousek\"}]\r\n";
	
	private static Date eventDate = new Date();
	
	private static List<PersonDTO> personList = new ArrayList<PersonDTO>();
	private static EventDTO javaForumEvent = new EventDTO(0L, "Javaforum Q1 2012", "Squeed", "Folkets Hus", sdf.format(eventDate));
	private static EventDTO nForumEvent = new EventDTO(0L, "nForum Q1 2012", "Squeed", "Folkets Hus", sdf.format(eventDate));
	
	private static List<EventDTO> events = new ArrayList<EventDTO>();
	
	private static List<RegistrationDTO> attendants = new ArrayList<RegistrationDTO>();
	static {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
	    JsonArray array = parser.parse(people).getAsJsonArray();

	    for(int a = 0; a < array.size(); a++) {
	    	PersonDTO personDto = gson.fromJson(array.get(a), PersonDTO.class);
	    	personDto.setId((long) a);
	    	personList.add(personDto);
	    	System.out.println("Added " + personList.get(a).getName() + " " +  personList.get(a).getMobilePhoneNr() + " " + personList.get(a).getUser());
	    }    
	    
	    Long attId = 0L;
	    for(PersonDTO person : personList) {
	    	attendants.add(new RegistrationDTO(attId++, person, javaForumEvent.getId(), 0));
	    }
	    
	    events.add(javaForumEvent);
	    events.add(nForumEvent);
	}
	
//	public List<EventDTO> getEvents() {
//		return events;
//	}
//	@Override
//	public List<RegistrationDTO> getAttendantsOfEvent() {
//		return attendants;
//	}
//	@Override
//	public void register(Long id) {
//		for(RegistrationDTO attDto : attendants) {
//			if(attDto.getId().equals(id)) {
//				attDto.setStatus(1);
//			}
//		}
//	}
//	@Override
//	public void register(String emailAddress) {
//		for(RegistrationDTO attDto : attendants) {
//			if(attDto.getPerson().getEmailAddress().equals(emailAddress)) {
//				attDto.setStatus(1);
//			}
//		}
//	}
//	@Override
//	public void unregister(Long id) {
//		for(RegistrationDTO attDto : attendants) {
//			if(attDto.getId().equals(id)) {
//				attDto.setStatus(0);
//			}
//		}
//	}
//	@Override
//	public void unregister(String emailAddress) {
//		for(RegistrationDTO attDto : attendants) {
//			if(attDto.getPerson().getEmailAddress().equals(emailAddress)) {
//				attDto.setStatus(0);
//			}
//		}
//	}
//	@Override
//	public void update(Long id, PersonDTO person) {
//		System.out.println("Updated request for person " + id);
//		personList.remove(person);
//		personList.add(person);
//	}
//
//	@Override
//	public List<EventInstance> getEvents() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Registration> getRegistrations(Long eventId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public EventInstance getEvent(Long eventId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Registration getRegistration(Long registrationId) {
//		for(RegistrationDTO attDto : attendants) {
//			if(attDto.getId().equals(registrationId)) {
//				return attDto.;
//			}
//		}
//	}
//
//	@Override
//	public void updateRegistration(Registration registration) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Registration createRegistration(Registration registration) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Person getPerson(Long personId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void updatePerson(Person person) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Person createPerson(Person person) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
