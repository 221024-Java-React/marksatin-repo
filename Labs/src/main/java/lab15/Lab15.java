package lab15;

import io.javalin.Javalin;
import lab15.controllers.PersonController;
import lab15.dao.PersonDao;
import lab15.dao.PersonDaoFile;
import lab15.exceptions.PersonDoesNotExistException;

public class Lab15 {
	
	public static void main(String[] args) {
		
		// Application Layer Instances
		PersonDao pDao = new PersonDaoFile();
		PersonController pController = new PersonController(pDao);
		
		
		// Javalin app setup
		Javalin app = Javalin.create(config -> {
			config.plugins.enableCors(cors -> {
				cors.add(it -> {
					it.anyHost();
				});
			});
		});
		
		
		// Route Handlers
		app.post("/person/add", pController.addPersonHandler);
		app.get("/person/get-all", pController.getAllPeopleHandler);
		app.put("/person/update", pController.updatePersonHandler);
		app.delete("/person/delete", pController.deletePersonHandler);
		
		
		// Exception Handlers
		app.exception(PersonDoesNotExistException.class, (e, ctx) -> {
			
			ctx.status(401);
			ctx.result("The person you are looking for does not exist.");
		});
		
		
		// Start Javalin app
		app.start(8080);
		
	}
	
}
