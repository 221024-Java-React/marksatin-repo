package com.proj1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proj1.models.UserRole;
import com.proj1.exceptions.UserAlreadyExistsException;
import com.proj1.models.User;
import com.proj1.utils.JDBCConnectionUtil;

public class UserDaoJDBC implements UserDao {
	
	private JDBCConnectionUtil dbConUtil = JDBCConnectionUtil.getInstance();

	@Override
	public void addUser(User u) {
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "INSERT INTO users (role, first_name, last_name, email, password) VALUES (?,?,?,?,?)";
			
			PreparedStatement prepState = connection.prepareStatement(sql);
			
			prepState.setInt(1, 1);		// Default to Employee which is role_id 1				
			prepState.setString(2, u.getFirstName());
			prepState.setString(3, u.getLastName());
			prepState.setString(4, u.getEmail());
			prepState.setString(5, u.getPassword());
			
			prepState.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserAlreadyExistsException();
		}
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> uList = new ArrayList<>();
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "SELECT * FROM users ORDER BY id ASC";
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				User u = new User();
				
				u.setId(rs.getInt(1));
				
				if (rs.getInt(2) == 1) {
					u.setRole(UserRole.EMPLOYEE);
				} else if (rs.getInt(2) == 2) {
					u.setRole(UserRole.MANAGER);
				} else {
					u.setRole(UserRole.ADMIN);
				}
				
				u.setFirstName(rs.getString(3));
				u.setLastName(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setPassword(rs.getString(6));
				
				uList.add(u);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return uList;
	}

	@Override
	public User getUserById(int id) {
		
		User u = null;
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "SELECT * FROM users WHERE id = ?";
			
			PreparedStatement prepState = connection.prepareStatement(sql);
			prepState.setInt(1, id);
			
			ResultSet rs = prepState.executeQuery();
			
			while (rs.next()) {
				u = new User();
				
				u.setId(rs.getInt(1));
				
				if (rs.getInt(2) == 1) {
					u.setRole(UserRole.EMPLOYEE);
				} else if (rs.getInt(2) == 2) {
					u.setRole(UserRole.MANAGER);
				} else {
					u.setRole(UserRole.ADMIN);
				}
				
				u.setFirstName(rs.getString(3));
				u.setLastName(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setPassword(rs.getString(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}
	
	@Override
	public User getUserByEmail(String email) {
	
		User u = null;
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "SELECT * FROM users WHERE email = ?";
			
			PreparedStatement prepState = connection.prepareStatement(sql);
			prepState.setString(1, email);
			
			ResultSet rs = prepState.executeQuery();
			
			while (rs.next()) {
				u = new User();
				
				u.setId(rs.getInt(1));
				
				if (rs.getInt(2) == 1) {
					u.setRole(UserRole.EMPLOYEE);
				} else if (rs.getInt(2) == 2) {
					u.setRole(UserRole.MANAGER);
				} else {
					u.setRole(UserRole.ADMIN);
				}
				
				u.setFirstName(rs.getString(3));
				u.setLastName(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setPassword(rs.getString(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}


	@Override
	public void updateUser(User u) {
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "UPDATE users SET role = ?, first_name = ?, last_name = ?, email = ?, password = ? WHERE id = ?";
			
			PreparedStatement prepState = connection.prepareStatement(sql);
			
			if (u.getRole().equals(UserRole.EMPLOYEE)) {
				prepState.setInt(1, 1);
			} else {
				prepState.setInt(1, 2);
			} 
			
			prepState.setString(2, u.getFirstName());
			prepState.setString(3, u.getLastName());
			prepState.setString(4, u.getEmail());
			prepState.setString(5, u.getPassword());
			prepState.setInt(6, u.getId());
			
			prepState.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int id) {
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "DELETE FROM users WHERE id = ?";
			
			PreparedStatement prepState = connection.prepareStatement(sql);
			prepState.setInt(1, id);
			
			prepState.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}