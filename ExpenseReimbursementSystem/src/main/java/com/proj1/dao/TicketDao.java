package com.proj1.dao;

import java.util.List;

import com.proj1.models.ExpenseType;
import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;

public interface TicketDao {
	
	public void addTicket(int userId, Ticket t);
	public void setTicketStatus(int ticketId, int resolverId, TicketStatus newStatus);
	public List<Ticket> getAllTickets();
	public List<Ticket> getTicketsByType(ExpenseType type);
	public List<Ticket> getTicketsByStatus(TicketStatus status);
	public Ticket getMostRecentTicket();
	public Ticket getTicketById(int ticketId);
}
