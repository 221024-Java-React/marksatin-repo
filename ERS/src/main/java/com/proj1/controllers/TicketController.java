package com.proj1.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj1.exceptions.LoggedOutException;
import com.proj1.models.Ticket;
import com.proj1.models.TicketStatus;
import com.proj1.services.TicketService;

import io.javalin.http.Handler;

public class TicketController<T> {

	private TicketService tServ;
	private ObjectMapper oMapper;
	
	public TicketController(TicketService tServ) {
		this.tServ = tServ;
		oMapper = new ObjectMapper();
	}
	
	public Handler handleSubmitNewTicket = (ctx) -> {
		Ticket t = oMapper.readValue(ctx.body(), Ticket.class);
		Object sessionUserId = ctx.req().getSession().getAttribute("user");
		
		if (sessionUserId == null) {
			throw new LoggedOutException();
		}
		
		t.setUserId((int) sessionUserId);
		
		System.out.println(t.getUserId());
		
		tServ.submitNewTicket(t);
		
		ctx.status(201);
		ctx.result(oMapper.writeValueAsString(t));
	};
	
	public Handler handleUpdateTicketStatus = (ctx) -> {
		Map<String, T> body = oMapper.readValue(ctx.body(), LinkedHashMap.class);
		
		int ticketId = (int) body.get("ticketId");
		TicketStatus newStatus = TicketStatus.valueOf(body.get("newStatus").toString());
		
		tServ.updateTicketStatus(ticketId, newStatus);
		
		ctx.status(200);
		ctx.result("Ticket #" + ticketId + " successfully updated.");
	};
	
}
