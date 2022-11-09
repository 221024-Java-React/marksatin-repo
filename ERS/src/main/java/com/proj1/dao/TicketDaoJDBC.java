package com.proj1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;
import com.proj1.utils.JDBCConnectionUtil;

public class TicketDaoJDBC implements TicketDao {

	private JDBCConnectionUtil dbConUtil = JDBCConnectionUtil.getInstance();

	@Override
	public void addNewTicket(Ticket t) {
		
		try {
			
			Connection connection = dbConUtil.getConnection();
			
			String sql = "INSERT INTO tickets (user_id, amount, description, status) VALUES (?,?,?,?)";
			
			PreparedStatement prepState = connection.prepareStatement(sql);
			
			prepState.setInt(1, t.getUserId());
			prepState.setFloat(2, t.getAmount());
			prepState.setString(3, t.getDescription());
			// TicketStatus is pending by default when adding a new ticket
			prepState.setInt(4, 1);
			
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
				Ticket t = new Ticket();
				
				t.setTicketId(rs.getInt(1));
				t.setUserId(rs.getInt(2));
				t.setAmount(rs.getFloat(3));
				t.setDescription(rs.getString(4));
				
				if (rs.getInt(5) == 1) {
					t.setStatus(TicketStatus.PENDING);
				} else if (rs.getInt(5) == 2) {
					t.setStatus(TicketStatus.APPROVED);
				} else {
					t.setStatus(TicketStatus.DENIED);
				}
				
				tList.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tList;
	}

	@Override
	public void setTicketStatus(int ticketId, TicketStatus newStatus) {
		
		try {
			Connection connection = dbConUtil.getConnection();
			
			String sql = "UPDATE tickets SET status = ? WHERE ticket_id = ?";
			
			PreparedStatement prepState = connection.prepareStatement(sql);
			
			if (newStatus.equals(TicketStatus.APPROVED)) {
				prepState.setInt(1, 2);
			} else {
				prepState.setInt(1, 3);
			}
			
			prepState.setInt(2, ticketId);
			
			prepState.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
