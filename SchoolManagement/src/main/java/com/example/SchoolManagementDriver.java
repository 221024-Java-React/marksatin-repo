package com.example;

import com.example.dao.FileIO;
import com.example.dao.PersonDao;
import com.example.dao.PersonDaoFile;
import com.example.models.Person;
import com.example.services.PersonService;

public class SchoolManagementDriver {
	
	public static void main(String[] args) {
//		Person p = new Person("Ethan", "McGill", 11122333l, true,  "email@mail.com", "password", 3.0);
//
//		FileIO<Person> personIO = new FileIO<Person>("person.txt");
//		
//		personIO.writeObject(p);
//		
//		System.out.println(personIO.readObject());
		
		// Before we setup our API routes, the easiest way to test our code is through this SchoolManagementDriver
		PersonDao pDao = new PersonDaoFile();
		PersonService pService = new PersonService(pDao);
		
		pService.registerPerson("Andrew", "Hunt", 111223333, false, "andrew@mail.com", "password", 4.0);
		
		System.out.println(pService.login("ethan@mail.com"));
		
		System.out.println(pDao.getAllPeople());
	}
	
}
