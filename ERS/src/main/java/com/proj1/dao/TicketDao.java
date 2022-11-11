package com.proj1.dao;

import java.util.List;

import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;

public interface TicketDao {
	
	public void addTicket(int userId, Ticket t);
	public void setTicketStatus(int ticketId, TicketStatus newStatus);
	public List<Ticket> getAllTickets();
	public List<Ticket> getTicketsByStatus(TicketStatus status);
	public Ticket getMostRecentTicket();
	public Ticket getTicketById(int ticketId);
}




	// getTicketsByStatus

	// MANAGER
		// can get all employee tickets
		// can sort by pending, approved, and denied
			// public List<Ticket> getTicketsByStatus(TicketStatus status);
		// can get all tickets
			// public List<Ticket> getAllTickets();


	// EMPLOYEE
		// can only get their own tickets
		// can sort by pending, approved, and denied
			// public List<Ticket> getTicketsByStatus(TicketStatus status);
		// can get all tickets
			// public List<Ticket> getAllTickets();
