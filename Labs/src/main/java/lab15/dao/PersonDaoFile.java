package lab15.dao;

import java.util.ArrayList;
import java.util.List;

import lab15.exceptions.PersonDoesNotExistException;
import lab15.models.Person;

public class PersonDaoFile implements PersonDao {

	private FileIO<List<Person>> io;
	
	@Override
	public void addPerson(Person p) {
		
		List<Person> pList = io.readObject();
		
		if (pList == null) {
			pList = new ArrayList<>();
		}
		
		pList.add(p);
		
		io.writeObject(pList);
	}

	@Override
	public List<Person> getAllPeople() {
		
		List<Person> pList = io.readObject();
		
		if (pList == null) {
			pList = new ArrayList<>();
		}
		
		return pList;
	}

	@Override
	public void updatePerson(Person p) {
		
		List<Person> pList = io.readObject();
		
		if (pList == null) {
			pList = new ArrayList<>();
		}
		
		for (Person pers : pList) {
			if (pers.getEmail().equals(p.getEmail())) {
				pList.remove(pers);
				pList.add(p);
				return;
			}
		}
		
		throw new PersonDoesNotExistException();
	}

	@Override
	public void deletePerson(String email) {

		List<Person> pList = io.readObject();
		
		if (pList == null) {
			pList = new ArrayList<>();
		}
		
		for (Person pers : pList) {
			if (pers.getEmail().equals(email)) {
				pList.remove(pers);
				return;
			}
		}
		
		throw new PersonDoesNotExistException();
	}
}
