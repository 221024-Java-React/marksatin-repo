package com.proj1.services;

import java.util.ArrayList;
import java.util.List;

import com.proj1.dao.UserDao;
import com.proj1.exceptions.InvalidCredentialsException;
import com.proj1.exceptions.UserAlreadyExistsException;
import com.proj1.exceptions.UserDoesNotExistException;
import com.proj1.models.User;

public class UserService {

	private UserDao userDao;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void registerUser(User u) {
		
		List<User> uList = new ArrayList<>();
		
		uList = userDao.getAllUsers();
		
		for (User listUser : uList) {
			if (listUser.getEmail() == u.getEmail()) {
				throw new UserAlreadyExistsException();
			}
		}
		
		userDao.addUser(u);	
	}
	
	public User loginUser(String email, String password) {
		User u = userDao.getUserByEmail(email);
		
		if (u == null) {
			throw new UserDoesNotExistException();
		}
		
		if (u.getPassword().equals(password)) {
			return u;
		} else {
			throw new InvalidCredentialsException();
		}
	}
	
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	public void updateUserInfo(User u) {
		userDao.updateUser(u);
	}
	
	public void removeUser(int id) {
		userDao.deleteUser(id);
	}
	
}
