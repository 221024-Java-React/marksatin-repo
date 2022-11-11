package com.proj1;

import com.proj1.dao.TicketDao;
import com.proj1.dao.TicketDaoJDBC;
import com.proj1.dao.UserDao;
import com.proj1.dao.UserDaoJDBC;
import com.proj1.exceptions.UserLoggedInException;
import com.proj1.exceptions.UserLoggedOutException;
import com.proj1.exceptions.TicketAlreadyProcessedException;
import com.proj1.exceptions.UserAlreadyExistsException;
import com.proj1.exceptions.UserDoesNotExistException;
import com.proj1.services.TicketService;
import com.proj1.services.UserService;
import com.proj1.controllers.TicketController;
import com.proj1.controllers.UserController;

import io.javalin.Javalin;

public class ExpenseReimbursementDriver {

	public static <T> void main(String[] args) {
		
		// Application Layer Instances
		UserDao uDao = new UserDaoJDBC();
		UserService uServ = new UserService(uDao);
		UserController uController = new UserController(uServ);
		
		TicketDao tDao = new TicketDaoJDBC();
		TicketService tServ = new TicketService(tDao);
		TicketController<T> tController = new TicketController<>(tServ, uServ);
		
		
		// Javalin app setup
		Javalin app = Javalin.create(config -> {
			config.plugins.enableCors(cors -> {
				cors.add(it -> {
					it.anyHost();
				});
			});
		});
		
		
		// Route Handlers
		app.post("/user/register", uController.registerHandler);
		app.post("/user/login", uController.loginHandler);
		app.get("/user/logout", uController.logoutHandler);
		app.get("/user/get-all", uController.getAllUsersHandler);
		app.put("/user/update", uController.updateUserHandler);
		app.delete("/user/delete", uController.deleteUserHandler);
		
		app.post("/ticket/add", tController.addTicketHandler);
		app.get("/ticket/get-all", tController.getAllTicketsHandler);
		app.get("/ticket/get-by-status", tController.getTicketsByStatusHandler);
		app.post("/ticket/update-status", tController.updateTicketStatusHandler);
		
		
		// Exception Handlers
		app.exception(UserAlreadyExistsException.class, (e, ctx) -> {
			ctx.status(401);
			ctx.result("This user is already registered in the system.");
		});
		
		app.exception(UserDoesNotExistException.class, (e, ctx) -> {
			ctx.status(401);
			ctx.result("This user does not exist in the user registry.");
		});
		
		app.exception(UserLoggedInException.class, (e, ctx) -> {
			ctx.status(403);
			ctx.result("You are already logged in.");
		});
		
		app.exception(UserLoggedOutException.class, (e, ctx) -> {
			ctx.status(403);
			ctx.result("You are currently logged out of the system\n\nPlease log in to access this feature.");
		});
		
		app.exception(TicketAlreadyProcessedException.class, (e, ctx) -> {
			ctx.status(403);
			ctx.result("This ticket has already been processed. You cannot update its status again.");
		});
		
		
		// Start Javalin app
		app.start(8000);
		
	}
}
