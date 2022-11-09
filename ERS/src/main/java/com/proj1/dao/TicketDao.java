package com.proj1.dao;

import java.util.List;

import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;

public interface TicketDao {
	
	public void addNewTicket(Ticket t);
	public List<Ticket> getAllTickets();
	public void setTicketStatus(int ticketId, TicketStatus newStatus);
//	public void removeTicket(Ticket t);
}
