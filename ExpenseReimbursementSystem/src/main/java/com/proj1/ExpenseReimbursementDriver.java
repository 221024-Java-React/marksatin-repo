package com.proj1;

import com.proj1.controllers.TicketController;
import com.proj1.controllers.UserController;
import com.proj1.dao.TicketDao;
import com.proj1.dao.TicketDaoJDBC;
import com.proj1.dao.UserDao;
import com.proj1.dao.UserDaoJDBC;
import com.proj1.exceptions.TicketAlreadyProcessedException;
import com.proj1.exceptions.UserAlreadyExistsException;
import com.proj1.exceptions.UserDoesNotExistException;
import com.proj1.exceptions.UserLoggedInException;
import com.proj1.exceptions.UserLoggedOutException;
import com.proj1.exceptions.UserRoleDoesNotExistException;
import com.proj1.services.TicketService;
import com.proj1.services.UserService;
import com.proj1.utils.LoggingUtil;

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
		app.get("/ticket/get-by-type", tController.getTicketsByTypeHandler);
		app.get("/ticket/get-by-status", tController.getTicketsByStatusHandler);
		app.put("/ticket/update-status", tController.updateTicketStatusHandler);
		
		
		// Exception Handlers
		app.exception(UserAlreadyExistsException.class, (e, ctx) -> {
			Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
			
			ctx.status(401);
			ctx.result("This user is already registered in the system.");
			LoggingUtil.getLogger().warn("An attempt was made to register with pre-existing User #" + userId + "'s credentials.");
		});
		
		app.exception(UserDoesNotExistException.class, (e, ctx) -> {
			ctx.status(401);
			ctx.result("This user does not exist in the user registry.");
			LoggingUtil.getLogger().warn("An attempt was made to retrieve a user that does not exist in the system.");
		});
		
		app.exception(UserLoggedInException.class, (e, ctx) -> {
			Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
			
			ctx.status(403);
			ctx.result("A user is already logged in.");
			LoggingUtil.getLogger().warn("User #" + userId + " tried to log in while already logged in.");
		});
		
		app.exception(UserLoggedOutException.class, (e, ctx) -> {
			ctx.status(403);
			ctx.result("You are currently logged out of the system\n\nPlease log in to access this feature.");
			LoggingUtil.getLogger().warn("A user attempted to access a feature while logged out.");
		});
		
		app.exception(TicketAlreadyProcessedException.class, (e, ctx) -> {
			Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
			
			ctx.status(403);
			ctx.result("This ticket has already been processed. You cannot update its status again.");
			LoggingUtil.getLogger().warn("User #" + userId + " attempted to reprocess a ticket after it was APPROVED or DENIED.");
		});
		
		app.exception(UserRoleDoesNotExistException.class, (e, ctx) -> {
			Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
			
			ctx.status(403);
			ctx.result("The User Role you entered does not exist.");
			LoggingUtil.getLogger().warn("User #" + userId + " attempted to assign a User Role that does not exist.");
		});
		
		
		// Start Javalin app
		app.start(8000);
	}
}
