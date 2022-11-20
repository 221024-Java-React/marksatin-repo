package lab15.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.http.Handler;
import lab15.dao.PersonDao;
import lab15.models.Person;

public class PersonController {

	private PersonDao pDao;
	private ObjectMapper oMapper;
	
	public PersonController(PersonDao pDao) {
		this.pDao = pDao;
		oMapper = new ObjectMapper();
	}
	
	public Handler addPersonHandler = (ctx) -> {
		Person p = oMapper.readValue(ctx.body(), Person.class);
		
		pDao.addPerson(p);
		
		ctx.status(201);
		ctx.result(oMapper.writeValueAsString(p));
	};
	
	public Handler getAllPeopleHandler = (ctx) -> {
		List<Person> pList = pDao.getAllPeople();
		
		ctx.status(200);
		ctx.result(oMapper.writeValueAsString(pList));
	};
	
	public Handler updatePersonHandler = (ctx) -> {
		Person p = oMapper.readValue(ctx.body(), Person.class);
		
		pDao.updatePerson(p);
		
		ctx.status(200);
		ctx.result("Person " + p.getName() + " was successfully updated in the system.");
	};
	
	public Handler deletePersonHandler = (ctx) -> {
		Map<String, String> body = oMapper.readValue(ctx.body(), LinkedHashMap.class);
		
		pDao.deletePerson(body.get("email"));
		
		ctx.status(200);
		ctx.result("Person with email " + body.get("email") + " was successfully deleted from the system.");
	};
}
