//package com.proj1.services;
//
//import java.util.ArrayList;
//
//import com.proj1.dao.TicketDao;
//import com.proj1.dao.UserDao;
//import com.proj1.models.CompanyRole;
//import com.proj1.models.Ticket;
//import com.proj1.models.TicketStatus;
//import com.proj1.models.User;
//
//public class TicketService {
//
//	private TicketDao ticketDao;
//	private UserDao userDao;
//	
//	public TicketService(TicketDao ticketDao) {
//		this.ticketDao = ticketDao;
//	}
//	
//	public void submitTicket(float amount, String description, TicketStatus status) {
//		Ticket t = new Ticket(amount, description, status);
//		ticketDao.addNewTicket(t);
//	}
//	
//	public void processTicket(Ticket t, TicketStatus status) {
//		t.setStatus(status);
//	}
//	
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
//	
//	// Manager update ticket status
//	//
//	// get pending Ticket
//	// remove it from pendTixList
//		// change its status
//	// only "Approved" or "Denied" accepted
//	// tix cannot change status after processing - final?
//		// add it to processedTickets.txt
//}
