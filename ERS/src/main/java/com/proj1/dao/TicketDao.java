package com.proj1.dao;

import java.util.ArrayList;

import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;

public interface TicketDao {
	
	public void addNewTicket(Ticket t);
	public ArrayList<Ticket> getAllTickets();
	public ArrayList<Ticket> getTicketsByStatus(TicketStatus status);
}
