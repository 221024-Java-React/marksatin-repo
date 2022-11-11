package com.proj1.exceptions;

public class TicketDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TicketDoesNotExistException() {
		super("The ticket you are looking for does not exist.");
	}
}
