package com.proj1.dao;

import java.util.List;

import com.proj1.models.User;
import com.proj1.models.UserRole;

public interface UserDao {

	public void addUser(User u);
	public List<User> getAllUsers();
	public User getUserById(int id);
	public User getUserByEmail(String email);
	public void updateUser(User u);
	public void updateUser(int userId, UserRole newRole);
	public void deleteUser(int id);
}
