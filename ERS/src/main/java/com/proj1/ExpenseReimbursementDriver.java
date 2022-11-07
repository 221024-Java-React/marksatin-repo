package com.proj1;

import com.proj1.dao.UserDao;
import com.proj1.dao.UserDaoJDBC;
import com.proj1.services.UserService;
import com.proj1.controllers.UserController;

import io.javalin.Javalin;

public class ExpenseReimbursementDriver {

	public static void main(String[] args) {
		
		// Daos, Services, Controllers connections go here
		UserDao uDao = new UserDaoJDBC();
		UserService uServ = new UserService(uDao);
		UserController uController = new UserController(uServ);
		
		
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
		
		
		
		// Start Javalin app
		app.start(8001);
		
	}
}
