package com.proj1.exceptions;

public class TicketDoesNotExistException extends RuntimeException {

	public TicketDoesNotExistException() {
		super("The ticket you are looking for does not exist.");
	}
}
