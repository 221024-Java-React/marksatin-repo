package com.proj1.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj1.models.UserRole;
import com.proj1.exceptions.UserDoesNotExistException;
import com.proj1.exceptions.UserLoggedInException;
import com.proj1.exceptions.UserLoggedOutException;
import com.proj1.models.User;
import com.proj1.services.UserService;

import io.javalin.http.Handler;

public class UserController {

	private UserService uServ;
	private ObjectMapper oMapper;
	
	public UserController(UserService uServ) {
		this.uServ = uServ;
		oMapper = new ObjectMapper();
	}
	
	public Handler registerHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId != null) {
			throw new UserLoggedInException();
		}
		
		User u = oMapper.readValue(ctx.body(), User.class);
		
		if (u.getEmail() != null && u.getPassword() != null) {
			uServ.registerUser(u);
			
			String firstName = "";
			
			if (u.getFirstName() != null) {
				firstName = " " + u.getFirstName() + "! ";
			} else {
				firstName = "! ";
			}
			
			ctx.status(201);
			ctx.result("Hi" + firstName + "Thank you for registering with ERS!");
			
		} else {
			ctx.status(401);
			ctx.result("You must register with an email and password.");
		}
	};
	
	public Handler loginHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId != null) {
			throw new UserLoggedInException();
		}
		
		Map<String, String> body = oMapper.readValue(ctx.body(), LinkedHashMap.class);		
		
		String email = body.get("email");
		String password = body.get("password");
		
		User u = uServ.loginUser(email, password);
		
		if (email != null && password != null && u != null) {
			// Set Session cookie
			ctx.req().getSession().setAttribute("user", u.getId());
			
			ctx.status(200);
			ctx.result("Welcome back " + u.getFirstName() + "! You are now logged in to ERS.");			
		} else {
			ctx.status(401);
			ctx.result("You must register with ERS before you can log in.");
		}
		
	};
	
	public Handler logoutHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId == null) {
			throw new UserLoggedOutException();
		}
		
		ctx.req().getSession().invalidate();
		
		ctx.status(200);
		ctx.result("You are now logged out of the system.\n\nThanks for using ERS. See you next time!");
	};
	
	public Handler getAllUsersHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId == null) {
			throw new UserLoggedOutException();
		}
		
		User currUser = uServ.getUserById(userId);
		
		// MANAGER/ADMIN ROUTE
		if (!currUser.getRole().equals(UserRole.EMPLOYEE)) {
			List<User> uList = uServ.getAllUsers();
			String usersString = "";
			
			for (User u : uList) {
				usersString += u + "\n\n";
			}
			
			ctx.status(200);
			ctx.result("The following users are registered with ERS:\n\n" + usersString);
		
		// EMPLOYEE ROUTE
		} else {
			ctx.status(401);
			ctx.result("You are not authorized to review the ERS user registry.");
		}
		
	};
	
	public Handler updateUserHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId == null) {
			throw new UserLoggedOutException();
		}
		
		User currUser = uServ.getUserById(userId);
		
		User u = oMapper.readValue(ctx.body(), User.class);
		
		// MANAGER/ADMIN ROUTE
		if (!currUser.getRole().equals(UserRole.EMPLOYEE)) {
			uServ.updateUser(u);
			
			ctx.status(200);
			ctx.result("You have successfully updated " + u.getFirstName() + " " + u.getLastName() + "'s information.");
			return;
		}
		
		// EMPLOYEE ROUTE
		if (userId == u.getId()) {
			uServ.updateUser(u);
			
			ctx.status(200);
			ctx.result("You have successfully updated your user information.");
			
		} else {
			ctx.status(401);
			ctx.result("You are not authorized to update another user's information.");
		}
	};
	
	public Handler deleteUserHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId == null) {
			throw new UserLoggedOutException();
		}
		
		User currUser = uServ.getUserById(userId);
		
		Map<String, Integer> body = oMapper.readValue(ctx.body(), LinkedHashMap.class);
		User delUser = uServ.getUserById(body.get("id"));
		
		if (delUser == null) {
			throw new UserDoesNotExistException();
		}
		
		// MANAGER/ADMIN ROUTE
		if (!currUser.getRole().equals(UserRole.EMPLOYEE)) {
			String delUserString = delUser.getFirstName() + " " + delUser.getLastName() + " ID #" + delUser.getId();
			
			uServ.removeUser(delUser.getId());
			
			ctx.status(200);
			ctx.result(delUserString + " has been removed from the ERS user registry.");
			return;
		}
		
		// EMPLOYEE ROUTE
		if (userId == delUser.getId()) {
			uServ.removeUser(delUser.getId());
			
			ctx.status(200);
			ctx.result("You have successfully deleted your account from the registry.");
		} else {
			ctx.status(401);
			ctx.result("You are not authorized to remove another user from the registry.");			
		}
	};
}
