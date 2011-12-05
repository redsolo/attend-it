package com.squeed.attendit.ejb.registration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.squeed.attendit.api.PersonDTO;
import com.squeed.attendit.db.entity.Event;
import com.squeed.attendit.db.entity.EventInstance;
import com.squeed.attendit.db.entity.ExternalIdService;
import com.squeed.attendit.db.entity.ExternalIdentifier;
import com.squeed.attendit.db.entity.Person;
import com.squeed.attendit.db.entity.Registration;
import com.squeed.attendit.db.entity.RegistrationStatus;
import com.squeed.attendit.db.entity.Setting;
import com.squeed.attendit.db.entity.User;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class SettingsBean implements Settings {
	private static final String DEFAULT_BOOTSTRAP_EMAIL = "erik.lupander@squeed.com";
	
	private static final String demoPeople = "[" +
	"{\"phone\": \"0733205177\", \"name\": \"Adam Lith\", \"user\": \"adam.lith\"}, " + 
	"{\"phone\": \"0722142558\", \"name\": \"Anders Sjöberg\", \"user\": \"anders.sjoberg\"}, " + 
	"{\"phone\": \"0703449075\", \"name\": \"Andreas Pringle\", \"user\": \"andreas.pringle\"}, " + 
	"{\"phone\": \"0702757015\", \"name\": \"Daniel Eriksson\", \"user\": \"daniel.eriksson\"}, " + 
	"{\"phone\": \"0733992721\", \"name\": \"Erik Lupander\", \"user\": \"erik.lupander\"}, " + 
	"{\"phone\": \"0768800763\", \"name\": \"Erik Ramfelt\", \"user\": \"erik.ramfelt\"}, " + 
	"{\"phone\": \"0739306403\", \"name\": \"Fredrik Corneliusson\", \"user\": \"fredrik.corneliusson\"}, " + 
	"{\"phone\": \"0727330054\", \"name\": \"Fredrik Normén\", \"user\": \"fredrik.normen\"}, " + 
	"{\"phone\": \"0702778511\", \"name\": \"Fredrik Wendt\", \"user\": \"fredrik.wendt\"}, " + 
	"{\"phone\": \"0702224459\", \"name\": \"Janne Hasslöf\", \"user\": \"janne.hasslof\"}, " + 
	"{\"phone\": \"0738515210\", \"name\": \"Johan Normén\", \"user\": \"johan.normen\"}, " + 
	"{\"phone\": \"0738531013\", \"name\": \"John Tjust\", \"user\": \"john.tjust\"}, " + 
	"{\"phone\": \"0736545987\", \"name\": \"Jonas Enlund\", \"user\": \"jonas.enlund\"}, " + 
	"{\"phone\": \"0739890278\", \"name\": \"Julia Klingvall\", \"user\": \"julia.klingvall\"}, " + 
	"{\"phone\": \"0733704722\", \"name\": \"Kristoffer Teuber\", \"user\": \"kristoffer.teuber\"}, " + 
	"{\"phone\": \"0708156463\", \"name\": \"Linus Norén\", \"user\": \"linus.noren\"}, " + 
	"{\"phone\": \"0761023854\", \"name\": \"Magnus Härlin\", \"user\": \"magnus.harlin\"}, " + 
	"{\"phone\": \"0708221771\", \"name\": \"Markus Wahl\", \"user\": \"markus.wahl\"}, " + 
	"{\"phone\": \"0708430248\", \"name\": \"Martin Jönsson\", \"user\": \"martin.jonsson\"}, " + 
	"{\"phone\": \"0730282823\", \"name\": \"Martin Woxneryd\", \"user\": \"martin.woxneryd\"}, " + 
	"{\"phone\": \"0702469109\", \"name\": \"Mats Nilsson\", \"user\": \"mats.nilsson\"}, " + 
	"{\"phone\": \"0736002767\", \"name\": \"Mattias Sandelin\", \"user\": \"mattias.sandelin\"}, " + 
	"{\"phone\": \"0702438761\", \"name\": \"Niclas Åstrand\", \"user\": \"niclas.astrand\"}, " + 
	"{\"phone\": \"0702022133\", \"name\": \"Niklas Johansson\", \"user\": \"niklas.johansson\"}, " + 
	"{\"phone\": \"0702109203\", \"name\": \"Ola Klasson\", \"user\": \"ola.klasson\"}, " + 
	"{\"phone\": \"0708618511\", \"name\": \"Peter Lindh\", \"user\": \"peter.lindh\"}, " + 
	"{\"phone\": \"0703286086\", \"name\": \"Pierre Ingmansson\", \"user\": \"pierre.ingmansson\"}, " + 
	"{\"phone\": \"0709100528\", \"name\": \"Rikard Thulin\", \"user\": \"rikard.thulin\"}, " + 
	"{\"phone\": \"0709723864\", \"name\": \"Tomas Trolltoft\", \"user\": \"tomas.trolltoft\"}, " + 
	"{\"phone\": \"0703333134\", \"name\": \"Torbjörn Karlsson\", \"user\": \"torbjorn.karlsson\"}, " + 
	"{\"phone\": \"0702751019\", \"name\": \"Wincent Papousek\", \"user\": \"wincent.papousek\"}]\r\n";
	
	

	private String status;

	private User user;
	
	@Inject
	EntityManager em;

	@PostConstruct

	public void init() {
		
		System.out.println("START init() of Settings!");
		if(em == null) {
			System.err.println("EntityManager was null, could not inject?");
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("attendit");			
			em = emf.createEntityManager();
			
		}
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			setupCoreInitData();
			setupDemoData();
			transaction.commit();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		status = "Started";
		
	}

	private void setupDemoData() {
		Query query = em.createQuery(
				"from Setting s where s.key=:p1")
				.setParameter("p1", Setting.DEMO_DATA_INITIALIZED);

		try {
			Setting setting = (Setting) query.getSingleResult();
		} catch (NoResultException nre) {
			createDemoData();
		}
	}

	private void createDemoData() {
		// Load bootstrap user
//		User u = null;
//		try {
//			u = (User) em.createQuery("select u from User u where u.email=:email").setParameter("email", DEFAULT_BOOTSTRAP_EMAIL).getSingleResult();
//		} catch(NoResultException nre) {
//			throw new IllegalStateException("Cannot create demo data, bootstrap user not found!");
//		}
		if(user == null) {
			throw new IllegalStateException("Cannot create demo data, bootstrap user not found!");
		}
		User u = user;
		List<User> administrators = new ArrayList<User>();
		administrators.add(u);
		
		// Create an external identifier
		ExternalIdService extIdService = new ExternalIdService();
		extIdService.setName("gravatar");
		extIdService.setServiceUrl("");
		extIdService = em.merge(extIdService);
		
		
		// Create Event "types"
		Event javaForum = new Event();
		javaForum.setCreatedBy(u);
		javaForum.setCreatedDate(Calendar.getInstance());
		javaForum.setDescription("JavaForum. By nerds, for nerds.");
		javaForum.setName("JavaForum Gothenburg");
		javaForum = em.merge(javaForum);
		
		Event nForum = new Event();
		nForum.setCreatedBy(u);
		nForum.setCreatedDate(Calendar.getInstance());
		nForum.setDescription("nForum. For the man in you.");
		nForum.setName("nForum Gothenburg");
		nForum = em.merge(nForum);
		
		// Create event instances
		EventInstance javaForumQ22012 = new EventInstance();
		javaForumQ22012.setAdministrators(administrators);
		javaForumQ22012.setCreatedBy(u);
		javaForumQ22012.setCreatedDate(Calendar.getInstance());
		javaForumQ22012.setDescription("JavaForum Göteborg Q2 2012");
		javaForumQ22012.setEvent(javaForum);
		javaForumQ22012.setEventDateTime(new GregorianCalendar(2012, 3, 5, 17, 0));
		javaForumQ22012.setLastRegistrationDate(new GregorianCalendar(2012, 2, 25));
		javaForumQ22012.setMaxAttendants(25);
		javaForumQ22012.setTitle("Javaforum Göteborg");
		javaForumQ22012 = em.merge(javaForumQ22012);
		System.out.println("Created Event Instance with ID: " + javaForumQ22012.getId());
		
		EventInstance nforumQ22012 = new EventInstance();
		nforumQ22012.setAdministrators(administrators);
		nforumQ22012.setCreatedBy(u);
		nforumQ22012.setCreatedDate(Calendar.getInstance());
		nforumQ22012.setDescription("nForum Göteborg Q2 2012");
		nforumQ22012.setEvent(nForum);
		nforumQ22012.setEventDateTime(new GregorianCalendar(2012, 3, 15, 17, 0));
		nforumQ22012.setLastRegistrationDate(new GregorianCalendar(2012, 2, 15));
		nforumQ22012.setMaxAttendants(125);
		nforumQ22012.setTitle("nForum Göteborg");
		nforumQ22012 = em.merge(nforumQ22012);
		
		// Load persons
		List<Person> persons = createDemoPersons(extIdService, javaForum);
		
		// Create registrations for javaforum
		//List<Registration> registrations = new ArrayList<Registration>();
		for(Person p : persons) {
			Registration reg = new Registration();
			reg.setEvent(javaForumQ22012);
			reg.setPerson(p);
			reg.setRegistrationDate(Calendar.getInstance());
			reg.setStatus(RegistrationStatus.REGISTERED);
			reg = em.merge(reg);		
			System.out.println("Created registration with ID: " + reg.getId());
		}
		
	}

	private List<Person> createDemoPersons(ExternalIdService externalIdService, Event javaForum) {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
	    JsonArray array = parser.parse(demoPeople).getAsJsonArray();
	    List<Person> personList = new ArrayList<Person>();
	    for(int a = 0; a < array.size(); a++) {
	    	PersonDTO dto = gson.fromJson(array.get(a), PersonDTO.class);
	    	Person person = new Person();
	    	person.mergeValuesFrom(dto);
	    	person.setEmail(dto.getUser() + "@squeed.com");
	    	
	    	person.setCreatedDate(Calendar.getInstance());
	    	person.getEventSubscriptions().add(javaForum);
	    	
	    	person = em.merge(person);
	    	
	    	ExternalIdentifier externalIdentifier = new ExternalIdentifier();
	    	externalIdentifier.setExternalIdService(externalIdService);
	    	externalIdentifier.setPerson(person);
	    	externalIdentifier.setValue(dto.getUser());
	    	externalIdentifier = em.merge(externalIdentifier);
	    	
	    	person.getExternalIdentifiers().add(externalIdentifier);
	    	
	    	personList.add(person);
	    
	    	
	    }
	   
	    return personList;
	}

	private void setupCoreInitData() {
		Query query = em.createQuery(
				"SELECT s from " + Setting.class.getName() + " s where s.key = ?1");
		
		query.setParameter(1, Setting.CORE_DATA_INITIALIZED);

		try {
			Setting setting = (Setting) query.getSingleResult();
		} catch (NoResultException nre) {
			createCoreInitData();
		}
	}

	private void createCoreInitData() {
		// Bootstrap user etc.
		User u = new User();
		u.setEmail(DEFAULT_BOOTSTRAP_EMAIL);
		u.setPassword("changeit");
		u.setCreatedDate(Calendar.getInstance());
		user = em.merge(u);
	}

}
