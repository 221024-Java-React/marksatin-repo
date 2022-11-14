package com.proj1.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj1.exceptions.UserLoggedOutException;
import com.proj1.exceptions.TicketAlreadyProcessedException;
import com.proj1.exceptions.TicketDoesNotExistException;
import com.proj1.models.UserRole;
import com.proj1.models.ExpenseType;
import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;
import com.proj1.models.User;
import com.proj1.services.TicketService;
import com.proj1.services.UserService;
import com.proj1.utils.LoggingUtil;
import com.proj1.utils.UtilMethods;

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
			
			if (t.getAmount() != 0.0f && t.getDescription() != null && t.getType() != null) {
				tServ.addTicket(userId, t);

				Ticket lastTicket = tServ.getMostRecentTicket();
				
				ctx.status(201);
				ctx.result("You have added the following receipt into the ERS ticketing system:\n\n" + oMapper.writeValueAsString(lastTicket));
				LoggingUtil.getLogger().info("User #" + userId + " added Ticket #" + lastTicket.getTicketId() + ".");
				
			} else {				
				ctx.status(401);
				ctx.result("You must include an amount and description in your receipt submission.");
				LoggingUtil.getLogger().warn("User #" + userId + " attempted to add a ticket without an amount or description.");
			}
			
		// MANAGER/ADMIN ROUTE
		} else {
			ctx.status(401);
			ctx.result("Only employees can submit receipts for reimbursement.");
			LoggingUtil.getLogger().warn("User #" + userId + " attempted to submit a ticket.");
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
			ctx.status(200);
			ctx.result("ALL RECEIPTS IN THE ERS:\n\n" + oMapper.writeValueAsString(ticketList));
			LoggingUtil.getLogger().info("User #" + userId + " retrieved all submitted tickets.");
			
		// EMPLOYEE ROUTE
		} else {
			List<Ticket> emplTicketList = new ArrayList<>();
			
			for (Ticket t : ticketList) {
				if (t.getUserId() == userId) {
					emplTicketList.add(t);					
				}
			}
			
			ctx.status(200);
			
			if (emplTicketList.size() > 0) {
				ctx.result("ALL RECEIPTS FOR EMPLOYEE " + currUser.getFirstName() + " " + currUser.getLastName() + ":\n\n" + oMapper.writeValueAsString(emplTicketList));
				LoggingUtil.getLogger().info("User #" + userId + " retrieved their past tickets.");
			} else {
				ctx.result("There are no receipts to display for " + currUser.getFirstName() + " " + currUser.getLastName() + ".");
				LoggingUtil.getLogger().info("User #" + userId + " attempted to retrieve their past tickets. Nothing to return.");
			}
		}
	};
	
	public Handler getTicketsByTypeHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId == null) {
			throw new UserLoggedOutException();
		}
		User currUser = uServ.getUserById(userId);
		
		Map<String, String> body = oMapper.readValue(ctx.body(), LinkedHashMap.class);
		String type = body.get("type");
		
		ExpenseType eType = UtilMethods.convertStringToType(type);
		
		List<Ticket> ticketList = tServ.getTicketsByType(eType);
		
		// MANAGER/ADMIN ROUTE
		if (!currUser.getRole().equals(UserRole.EMPLOYEE)) {
			
			ctx.status(200);
			
			if (ticketList.size() > 0) {
				ctx.result("ALL " + type + " RECEIPTS:\n\n" + oMapper.writeValueAsString(ticketList));
				LoggingUtil.getLogger().info("User #" + userId + " retrieved all " + type + " tickets.");
				
			} else {
				ctx.result("There are no " + type + " receipts to display.");
				LoggingUtil.getLogger().info("User #" + userId + " attempted to retrieve all " + type + " tickets. Nothing to return.");
			}
			
		// EMPLOYEE ROUTE
		} else {
			List<Ticket> emplTicketList = new ArrayList<>();
			
			for (Ticket t : ticketList) {
				if (t.getUserId() == userId) {
					emplTicketList.add(t);					
				}
			}
			
			ctx.status(200);
			
			if (emplTicketList.size() > 0) {
				ctx.result("ALL " + type + " RECEIPTS FOR EMPLOYEE " + currUser.getFirstName() + " " + currUser.getLastName() + ":\n\n" + oMapper.writeValueAsString(emplTicketList));
				LoggingUtil.getLogger().info("User #" + userId + " retrieved their " + type + " tickets.");
				
			} else {
				ctx.result("There are no " + type + " receipts to display.");
				LoggingUtil.getLogger().info("User #" + userId + " attemtped to retrieve their " + type + " tickets. Nothing to return.");
			}
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
		
		TicketStatus tStatus = UtilMethods.convertStringToStatus(status);

		List<Ticket> ticketList = tServ.getTicketsByStatus(tStatus);
		
		// MANAGER/ADMIN ROUTE
		if (!currUser.getRole().equals(UserRole.EMPLOYEE)) {
			
			ctx.status(200);
			
			if (ticketList.size() > 0) {
				ctx.result("ALL " + status + " RECEIPTS:\n\n" + oMapper.writeValueAsString(ticketList));
				LoggingUtil.getLogger().info("User #" + userId + " retrieved all " + status + " tickets.");
				
			} else {
				ctx.result("There are no " + status + " receipts to display.");
				LoggingUtil.getLogger().info("User #" + userId + " attempted to retrieve all " + status + " tickets. Nothing to return.");
			}
			
		// EMPLOYEE ROUTE
		} else {
			List<Ticket> emplTicketList = new ArrayList<>();
			
			for (Ticket t : ticketList) {
				if (t.getUserId() == userId) {
					emplTicketList.add(t);					
				}
			}
			
			ctx.status(200);
			
			if (emplTicketList.size() > 0) {
				ctx.result("ALL " + status + " RECEIPTS FOR EMPLOYEE " + currUser.getFirstName() + " " + currUser.getLastName() + ":\n\n" + oMapper.writeValueAsString(emplTicketList));
				LoggingUtil.getLogger().info("User #" + userId + " retrieved their " + status + " tickets.");
				
			} else {
				ctx.result("There are no " + status + " receipts to display.");
				LoggingUtil.getLogger().info("User #" + userId + " attemtped to retrieve their " + status + " tickets. Nothing to return.");
			}
		}
	};
	
	public Handler updateTicketStatusHandler = (ctx) -> {
		Integer userId = (Integer) ctx.req().getSession().getAttribute("user");
		
		if (userId == null) {
			throw new UserLoggedOutException();
		}
		User currUser = uServ.getUserById(userId);
		
		Map<String, T> body = oMapper.readValue(ctx.body(), LinkedHashMap.class);
		int ticketId = (int) body.get("ticketId");
		
		// MANAGER/ADMIN ROUTE
		if (!currUser.getRole().equals(UserRole.EMPLOYEE)) {
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
						tServ.updateTicketStatus(ticketId, userId, newStatus);
						
						ctx.status(200);
						ctx.result("User #" + userId + " approved ticket #" + ticketId + " for reimbursement.");
						LoggingUtil.getLogger().info("User #" + userId + " approved ticket #" + ticketId + ".");
						break;
						
					case DENIED:
						tServ.updateTicketStatus(ticketId, userId, newStatus);
						
						ctx.status(200);
						ctx.result("User #" + userId + " approved ticket #" + ticketId + " for reimbursement.");
						LoggingUtil.getLogger().info("User #" + userId + " approved ticket #" + ticketId + ".");
						break;
						
					default:
						ctx.status(403);
						ctx.result("Managers must either approve or deny pending receipts.");
						LoggingUtil.getLogger().warn("User #" + userId + " attempted to change ticket #" + ticketId + " to a status other than APPROVED or DENIED.");
						break;
					}
				
			} catch (IllegalArgumentException e) {
				ctx.status(403);
				ctx.result("Managers must either approve or deny pending receipts.");
				LoggingUtil.getLogger().warn("User #" + userId + " attempted to change ticket #" + ticketId + " to a status other than APPROVED or DENIED.");
			}
			
		// EMPLOYEE ROUTE
		} else {
			ctx.status(403);
			ctx.result("Employees cannot approve or deny pending receipts.");
			LoggingUtil.getLogger().warn("User #" + userId + " attempted to approve or deny ticket #" + ticketId + ".");
		}
	};
}
