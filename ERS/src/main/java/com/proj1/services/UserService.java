package com.proj1.services;

import java.util.List;

import com.proj1.dao.UserDao;
import com.proj1.exceptions.InvalidCredentialsException;
import com.proj1.exceptions.UserDoesNotExistException;
import com.proj1.models.User;

public class UserService {

	private UserDao uDao;
	
	public UserService(UserDao uDao) {
		this.uDao = uDao;
	}
	
	public void registerUser(User u) {
		uDao.addUser(u);				
	}
	
	public User loginUser(String email, String password) {
		
		User u = null;
		
		try {
			u = uDao.getUserByEmail(email);
			u.getPassword().equals(password); 
			
		} catch (UserDoesNotExistException e) {
			e.printStackTrace();
		} catch (InvalidCredentialsException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return u;
	}
	
	public List<User> getAllUsers() {
		return uDao.getAllUsers();
	}
	
	public User getUserById(int id) {
		return uDao.getUserById(id);
	}
	
	public void updateUser(User u) {
		uDao.updateUser(u);
	}
	
	public void removeUser(int id) {
		uDao.deleteUser(id);
	}
	
}
