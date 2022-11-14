package com.proj1.services;

import java.util.List;

import com.proj1.dao.TicketDao;
import com.proj1.exceptions.TicketAlreadyProcessedException;
import com.proj1.exceptions.TicketDoesNotExistException;
import com.proj1.models.ExpenseType;
import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;

public class TicketService {

	private TicketDao tDao;
	
	public TicketService(TicketDao tDao) {
		this.tDao = tDao;
	}
	
	public void addTicket(int userId, Ticket t) {
		tDao.addTicket(userId, t);
	}
	
	public void updateTicketStatus(int ticketId, int resolverId, TicketStatus newStatus) {
		tDao.setTicketStatus(ticketId, resolverId, newStatus);
	}
	
	public List<Ticket> getAllTickets() {
		return tDao.getAllTickets();
	}
	
	public List<Ticket> getTicketsByType(ExpenseType type) {
		return tDao.getTicketsByType(type);
	}
	
	public List<Ticket> getTicketsByStatus(TicketStatus status) {
		return tDao.getTicketsByStatus(status);
	}
	
	public Ticket getMostRecentTicket() {
		return tDao.getMostRecentTicket();
	}
	
	public Ticket getTicketById(int ticketId) {
		return tDao.getTicketById(ticketId);
	}
}
