package com.example.services;

import com.example.dao.PersonDao;
import com.example.exceptions.PersonDoesNotExistException;
import com.example.models.Person;

public class PersonService {

	private PersonDao personDao;
	
	// Dependency injection - allows us to change components of the same "type" easily
	public PersonService(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public void registerPerson(String first, String last, int ssn, boolean faculty, String email, String password, double gpa) {
		Person p;
		try {
			p = personDao.getPersonByEmail(email);
			// Throw an exception if the user exists when trying to register
		} catch (PersonDoesNotExistException e) {
			p = new Person(first, last, ssn, faculty, email, password, gpa);
			
			personDao.addPerson(p);
		}
	}
	
	public Person login(String email) {
		Person p = personDao.getPersonByEmail(email);
		
		if (p == null) {
			// Better to throw an invalid credentials
			return null;
		}
		
		return p;
	}
	
}
