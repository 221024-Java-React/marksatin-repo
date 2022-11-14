package com.proj1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proj1.models.ExpenseType;
import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;
import com.proj1.utils.JDBCConnectionUtil;
import com.proj1.utils.UtilMethods;

public class TicketDaoJDBC implements TicketDao {

	private JDBCConnectionUtil dbConUtil = JDBCConnectionUtil.getInstance();

	@Override
	public void addTicket(int userId, Ticket t) {
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "INSERT INTO tickets (user_id, amount, description, type, status) VALUES (?,?,?,?,?)";
			
			PreparedStatement prepState = connection.prepareStatement(sql);
			
			int colNum = UtilMethods.convertTypeToInt(t.getType());
			
			prepState.setInt(1, userId);
			prepState.setFloat(2, t.getAmount());
			prepState.setString(3, t.getDescription());
			prepState.setInt(4, colNum);
			prepState.setInt(5, 1);	// TicketStatus defaults to 1 (PENDING)
			prepState.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setTicketStatus(int ticketId, int resolverId, TicketStatus newStatus) {
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "UPDATE tickets SET status = ?, resolver_id = ? WHERE ticket_id = ?";
			
			PreparedStatement prepState = connection.prepareStatement(sql);
			
			int colNum = UtilMethods.convertStatusToInt(newStatus);
			
			prepState.setInt(1, colNum);
			prepState.setInt(2, resolverId);
			prepState.setInt(3, ticketId);
			prepState.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Ticket> getAllTickets() {
		
		List<Ticket> tList = new ArrayList<>();
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "SELECT * FROM tickets";
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Ticket t = UtilMethods.createTicketFromDb(rs);
				tList.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tList;
	}
	
	@Override
	public List<Ticket> getTicketsByType(ExpenseType type) {
		
		List<Ticket> tList = new ArrayList<>();
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "SELECT * FROM tickets WHERE type = ?";
			
			PreparedStatement prepState = connection.prepareStatement(sql);
			
			int colNum = UtilMethods.convertTypeToInt(type);
			
			prepState.setInt(1, colNum);
			
			ResultSet rs = prepState.executeQuery();
			while (rs.next()) {
				Ticket t = UtilMethods.createTicketFromDb(rs);
				tList.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tList;
	}
	
	@Override
	public List<Ticket> getTicketsByStatus(TicketStatus status) {
		
		List<Ticket> tList = new ArrayList<>();
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "SELECT * FROM tickets WHERE status = ?";
			
			PreparedStatement prepState = connection.prepareStatement(sql);
			
			int colNum = UtilMethods.convertStatusToInt(status);
			
			prepState.setInt(1, colNum);
			
			ResultSet rs = prepState.executeQuery();
			while (rs.next()) {
				Ticket t = UtilMethods.createTicketFromDb(rs);
				tList.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tList;
	}
	
	@Override
	public Ticket getMostRecentTicket() {

		Ticket t = null;
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "SELECT * FROM tickets ORDER BY ticket_id DESC LIMIT 1";
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				t = UtilMethods.createTicketFromDb(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	@Override
	public Ticket getTicketById(int ticketId) {
		
		Ticket t = null;
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "SELECT * FROM tickets WHERE ticket_id = ?";
			
			PreparedStatement prepState = connection.prepareStatement(sql);
			prepState.setInt(1, ticketId);
			
			ResultSet rs = prepState.executeQuery();		
			while (rs.next()) {
				t = UtilMethods.createTicketFromDb(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}
}
