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
import com.proj1.utils.LoggingUtil;

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
			LoggingUtil.getLogger().info("User #" + u.getId() + " successfully registered.");
			
		} else {
			ctx.status(401);
			ctx.result("You must register with an email and password.");
			LoggingUtil.getLogger().warn("New user tried to register without an email or password.");
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
			LoggingUtil.getLogger().info("User #" + u.getId() + " logged in.");
		} else {
			ctx.status(401);
			ctx.result("You must register with ERS before you can log in.");
			LoggingUtil.getLogger().warn("An unregistered user tried to log in.");
		}
		
	};
	
	public Handler logoutHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId == null) {
			throw new UserLoggedOutException();
		}
		// Delete session cookie
		ctx.req().getSession().invalidate();
		
		ctx.status(200);
		ctx.result("You are now logged out of the system.\n\nThanks for using ERS. See you next time!");
		LoggingUtil.getLogger().info("User #" + userId + " logged out.");
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
			
			ctx.status(200);
			ctx.result("The following users are registered with ERS:\n\n" + oMapper.writeValueAsString(uList));
			LoggingUtil.getLogger().info("User #" + userId + " retrieved all user data.");
		
		// EMPLOYEE ROUTE
		} else {
			ctx.status(200);
			ctx.result("Your user info:\n\n" + oMapper.writeValueAsString(currUser));
			LoggingUtil.getLogger().info("User #" + userId + " retrieved their user data.");
		}
		
	};
	
	public Handler updateUserHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId == null) {
			throw new UserLoggedOutException();
		}
		User currUser = uServ.getUserById(userId);
		
		User u = oMapper.readValue(ctx.body(), User.class);
		
		// ADMIN ROUTE
		if (currUser.getRole().equals(UserRole.ADMIN)) {
			uServ.updateUser(u);
			
			ctx.status(200);
			
			if (u.getId() == userId) {
				ctx.result("You have successfully updated your user information.");
				LoggingUtil.getLogger().info("User #" + userId + " updated their user information.");
			} else {
				ctx.result("You have successfully updated User #" + u.getId() + "'s information.");
				LoggingUtil.getLogger().info("User #" + userId + " updated User #" + u.getId() + "'s information.");
			}
			return;
			
		// MANAGER ROUTE
		} else if (currUser.getRole().equals(UserRole.MANAGER)) {
			
			// If updating their own info, they can change anything
			if (u.getId() == userId) {
				uServ.updateUser(u);
				
				ctx.status(200);
				ctx.result("You have successfully updated your user information.");
				LoggingUtil.getLogger().info("User #" + userId + " updated their own information.");
				
			// If updating another user's info	
			} else {
				// they can only change UserRole (to EMPLOYEE or MANAGER)
				if (u.getRole().equals(UserRole.EMPLOYEE) || u.getRole().equals(UserRole.MANAGER)) {
					uServ.updateUser(u.getId(), u.getRole());
					
					ctx.status(200);
					ctx.result("User #" + userId + " changed User #" + u.getId() + "'s user role to " + u.getRole() + ".");
					LoggingUtil.getLogger().info("User #" + userId + " changed User #" + u.getId() + "'s user role to " + u.getRole() + ".");
				} else {
					ctx.status(401);
					ctx.result("You can only change a user's role to EMPLOYEE or MANAGER.");
					LoggingUtil.getLogger().warn("User #" + userId + "attempted to change User #" + u.getId() + "'s user role to " + u.getRole() + ".");
				}
			}
			
		// EMPLOYEE ROUTE
		} else if (currUser.getRole().equals(UserRole.EMPLOYEE)) {
			
			if (!u.getRole().equals(currUser.getRole())) {
				ctx.status(401);
				ctx.result("You are not authorized to change your user role.");
				LoggingUtil.getLogger().warn("User #" + userId + " attempted to chagne their user role.");
				return;
			}
			
			if (u.getId() == userId) {
				uServ.updateUser(u);
				
				ctx.status(200);
				ctx.result("You have successfully updated your user information.");
				LoggingUtil.getLogger().info("User #" + userId + " updated their own information.");
				
			} else {
				ctx.status(401);
				ctx.result("You are not authorized to update another user's information.");
				LoggingUtil.getLogger().warn("User #" + userId + " attempted to update another user's information.");
			}
			
			
			
		} else {
			throw new UserDoesNotExistException();
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
			uServ.removeUser(delUser.getId());
			
			ctx.status(200);
			ctx.result("User #" + delUser.getId() + " has been removed from the system.");
			LoggingUtil.getLogger().info("User #" + userId + " removed User #" + delUser.getId() + " from the system.");
			return;
		}
		
		// EMPLOYEE ROUTE
		if (userId == delUser.getId()) {
			uServ.removeUser(delUser.getId());
			
			ctx.status(200);
			ctx.result("You have successfully deleted your account from the registry.");
			LoggingUtil.getLogger().info("User #" + userId + " removed themself from the system.");
			
		} else {
			ctx.status(401);
			ctx.result("You are not authorized to remove another user from the registry.");			
			LoggingUtil.getLogger().warn("User #" + userId + " attempted to remove another user from the system.");
		}
	};
}
