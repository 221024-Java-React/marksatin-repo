package com.proj1.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj1.exceptions.UserLoggedOutException;
import com.proj1.exceptions.TicketAlreadyProcessedException;
import com.proj1.exceptions.TicketDoesNotExistException;
import com.proj1.models.UserRole;
import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;
import com.proj1.models.User;
import com.proj1.services.TicketService;
import com.proj1.services.UserService;

import io.javalin.http.Handler;

public class TicketController<T> {

	private TicketService tServ;
	private UserService uServ;
	private ObjectMapper oMapper;
	
	public TicketController(TicketService tServ, UserService uServ) {
		this.tServ = tServ;
		this.uServ = uServ;
		oMapper = new ObjectMapper();
	}
	
	public Handler addTicketHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId == null) {
			throw new UserLoggedOutException();
		}
		
		User currUser = uServ.getUserById(userId);
		
		// EMPLOYEE ROUTE
		if (currUser.getRole().equals(UserRole.EMPLOYEE)) {
			Ticket t = oMapper.readValue(ctx.body(), Ticket.class);
			
			if (t.getAmount() != 0.0f && t.getDescription() != null) {
				tServ.addTicket(userId, t);

				Ticket lastTicket = tServ.getMostRecentTicket();
				
				ctx.status(201);
				ctx.result("You have added the following receipt into the ERS ticketing system:\n\n" + oMapper.writeValueAsString(lastTicket));
			} else {
				ctx.status(401);
				ctx.result("You must include an amount and description in your receipt submission.");
			}
			
		// MANAGER/ADMIN ROUTE
		} else {
			ctx.status(401);
			ctx.result("Only employees can submit receipts for reimbursement.");
		}
	};
	
	public Handler getAllTicketsHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId == null) {
			throw new UserLoggedOutException();
		}
		
		User currUser = uServ.getUserById(userId);
		List<Ticket> ticketList = tServ.getAllTickets();
		
		// MANAGER/ADMIN ROUTE
		if (!currUser.getRole().equals(UserRole.EMPLOYEE)) {
			String ticketsString = "";
			
			for (Ticket t : ticketList) {
				ticketsString += t + "\n\n";
			}
			
			ctx.status(200);
			ctx.result("ALL RECEIPTS IN THE ERS:\n\n" + ticketsString);
			
		// EMPLOYEE ROUTE
		} else {
			String ticketsString = "";
			
			for (Ticket t : ticketList) {
				if (t.getUserId() == userId) {
					ticketsString += t + "\n\n";					
				}
			}
			
			ctx.status(200);
			ctx.result("ALL RECEIPTS FOR EMPLOYEE " + currUser.getFirstName() + " " + currUser.getLastName() + ":\n\n" + ticketsString);
		}
	};
	
	public Handler getTicketsByStatusHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId == null) {
			throw new UserLoggedOutException();
		}
		
		User currUser = uServ.getUserById(userId);
		
		Map<String, String> body = oMapper.readValue(ctx.body(), LinkedHashMap.class);
		
		String status = body.get("status");
		TicketStatus tStatus = null;
		
		switch (status) {
			case "PENDING":
				tStatus = TicketStatus.PENDING;
				break;
			case "APPROVED":
				tStatus = TicketStatus.APPROVED;
				break;
			case "DENIED":
				tStatus = TicketStatus.DENIED;
				break;
		}
		
		List<Ticket> ticketList = tServ.getTicketsByStatus(tStatus);
		
		// MANAGER/ADMIN ROUTE
		if (!currUser.getRole().equals(UserRole.EMPLOYEE)) {
			String ticketsString = "";
			
			for (Ticket t : ticketList) {
				ticketsString += t + "\n\n";
			}
			
			ctx.status(200);
			if (ticketList.size() > 0) {
				ctx.result("ALL " + status + " RECEIPTS:\n\n" + ticketsString);
			} else {
				ctx.result("There are no " + status + " receipts to display.");
			}
			
		// EMPLOYEE ROUTE
		} else {
			String ticketsString = "";
			
			for (Ticket t : ticketList) {
				if (t.getUserId() == userId) {
					ticketsString += t + "\n\n";					
				}
			}
			
			ctx.status(200);
			if (ticketList.size() > 0) {
				ctx.result("ALL " + status + " RECEIPTS FOR EMPLOYEE " + currUser.getFirstName() + " " + currUser.getLastName() + ":\n\n" + ticketsString);
			} else {
				ctx.result("There are no " + status + " receipts to display.");
			}
		}	
	};
	
	public Handler updateTicketStatusHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId == null) {
			throw new UserLoggedOutException();
		}
		
		User currUser = uServ.getUserById(userId);
		
		// MANAGER/ADMIN ROUTE
		if (!currUser.getRole().equals(UserRole.EMPLOYEE)) {
			Map<String, T> body = oMapper.readValue(ctx.body(), LinkedHashMap.class);
			
			int ticketId = (int) body.get("ticketId");
			Ticket t = tServ.getTicketById(ticketId);
			
			if (tServ.getTicketById(ticketId) ==  null) {
				throw new TicketDoesNotExistException();
			}
			
			if (!t.getStatus().equals(TicketStatus.PENDING)) {
				throw new TicketAlreadyProcessedException();
			}
			
			try {
				TicketStatus newStatus = TicketStatus.valueOf(body.get("newStatus").toString().toUpperCase());			
				
				switch (newStatus) {
					case APPROVED:
						tServ.updateTicketStatus(ticketId, newStatus);
						ctx.status(200);
						ctx.result("Ticket #" + ticketId + " has been APPROVED for reimbursement.");
						break;
					case DENIED:
						tServ.updateTicketStatus(ticketId, newStatus);
						ctx.status(200);
						ctx.result("Ticket #" + ticketId + " has been DENIED for reimbursement.");
						break;
					default:
						ctx.status(403);
						ctx.result("Managers must either approve or deny pending receipts.");
						break;
					}
				
			} catch (IllegalArgumentException e) {
				ctx.status(403);
				ctx.result("Managers must either approve or deny pending receipts.");
			}
			
		// EMPLOYEE ROUTE
		} else {
			ctx.status(403);
			ctx.result("Employees cannot approve or deny pending receipts.");
		}
	};
}
