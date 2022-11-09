package com.proj1;

import com.proj1.dao.TicketDao;
import com.proj1.dao.TicketDaoJDBC;
import com.proj1.dao.UserDao;
import com.proj1.dao.UserDaoJDBC;
import com.proj1.services.TicketService;
import com.proj1.services.UserService;
import com.proj1.controllers.TicketController;
import com.proj1.controllers.UserController;

import io.javalin.Javalin;

public class ExpenseReimbursementDriver {

	public static void main(String[] args) {
		
		// Daos, Services, Controllers connections go here
		UserDao uDao = new UserDaoJDBC();
		UserService uServ = new UserService(uDao);
		UserController uController = new UserController(uServ);
		
		TicketDao tDao = new TicketDaoJDBC();
		TicketService tServ = new TicketService(tDao);
		TicketController tController = new TicketController(tServ);
		
		
		// Javalin app setup
		Javalin app = Javalin.create(config -> {
			config.plugins.enableCors(cors -> {
				cors.add(it -> {
					it.anyHost();
				});
			});
		});
		
		// Routes/Handlers
		app.post("/user/register", uController.handleRegister);
		app.post("/user/login", uController.handleLogin);
		app.get("/user/", uController.handleGetAllUsers);
		app.put("/user/", uController.handleUpdate);
		app.delete("/user/", uController.handleDelete);
		app.get("/user/logout", uController.handleLogout);
		
		app.post("/ticket/submit-ticket", tController.handleSubmitNewTicket);
		app.post("/ticket/update-status", tController.handleUpdateTicketStatus);
		
		
		
		// Start Javalin app
		app.start(8001);
		
	}
}
