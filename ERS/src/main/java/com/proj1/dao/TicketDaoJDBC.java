package com.proj1.dao;

import java.util.ArrayList;

import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;
import com.proj1.utils.JDBCConnectionUtil;

public class TicketDaoJDBC implements TicketDao {

	private JDBCConnectionUtil dbConUtil = JDBCConnectionUtil.getInstance();

	@Override
	public void addNewTicket(Ticket t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Ticket> getAllTickets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ticket> getTicketsByStatus(TicketStatus status) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
