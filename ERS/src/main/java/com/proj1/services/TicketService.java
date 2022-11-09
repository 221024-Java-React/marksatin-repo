package com.proj1.services;

import com.proj1.dao.TicketDao;
import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;

public class TicketService {

	private TicketDao ticketDao;
	
	public TicketService(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
	
	public void submitNewTicket(Ticket t) {
		ticketDao.addNewTicket(t);
	}
	
	public void updateTicketStatus(int ticketId, TicketStatus newStatus) {
		ticketDao.setTicketStatus(ticketId, newStatus);
	}
	
//	public ArrayList<Ticket> getSubmittedTicketsByEmployee(String email) {
//		User u = userDao.getUserByEmail(email);
//		ArrayList<Ticket> submittedTickets = new ArrayList<Ticket>();
//				
//		if (u.getRole().equals(CompanyRole.EMPLOYEE)) {
//			submittedTickets = u.getSubmittedTickets();			
//		} else {
//			submittedTickets = null;
//		}
//		
//		return submittedTickets;
//	}
	
}
