package com.proj1.exceptions;

public class TicketAlreadyProcessedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TicketAlreadyProcessedException() {
		super("This ticket has already been processed by a manager. It's status cannot be updated again.");		
	}
}
