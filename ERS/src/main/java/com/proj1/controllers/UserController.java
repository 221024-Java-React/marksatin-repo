package com.proj1.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	public Handler handleRegister = (ctx) -> {
		User u = oMapper.readValue(ctx.body(), User.class);
		uServ.registerUser(u);
		
		ctx.status(201);
		ctx.result(oMapper.writeValueAsString(u));
	};
	
	public Handler handleLogin = (ctx) -> {
		Map<String, String> body = oMapper.readValue(ctx.body(), LinkedHashMap.class);		
		User loggedInUser = uServ.loginUser(body.get("email"), body.get("password"));
		
		// Set Session cookie
		ctx.req().getSession().setAttribute("user", loggedInUser.getId());
		
		ctx.status(200);
		ctx.result(oMapper.writeValueAsString(loggedInUser));
	};
	
	public Handler handleGetAllUsers = (ctx) -> {
		List<User> uList = uServ.getAllUsers();
		
		ctx.status(200);
		ctx.result(oMapper.writeValueAsString(uList));
	};
	
	public Handler handleUpdate = (ctx) -> {
		User u = oMapper.readValue(ctx.body(), User.class);
		uServ.updateUserInfo(u);
		
		ctx.status(200);
		ctx.result("SUCCESS: The user's data was updated.");
	};
	
	public Handler handleDelete = (ctx) -> {
		Map<String, Integer> body = oMapper.readValue(ctx.body(), LinkedHashMap.class);
		uServ.removeUser(body.get("id"));
		
		ctx.status(204);
		ctx.result("SUCCESS: The user's data was deleted.");
	};
	
	public Handler handleLogout = (ctx) -> {
		ctx.req().getSession().invalidate();
		
		ctx.status(200);
		ctx.result("You have successfully logged out.");
	};
}
