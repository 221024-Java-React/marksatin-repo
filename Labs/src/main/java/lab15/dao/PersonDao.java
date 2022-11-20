package lab15.dao;

import java.util.List;

import lab15.models.Person;

public interface PersonDao {
	
	public void addPerson(Person p);
	public List<Person> getAllPeople();
	public void updatePerson(Person p);
	public void deletePerson(String email);
}
