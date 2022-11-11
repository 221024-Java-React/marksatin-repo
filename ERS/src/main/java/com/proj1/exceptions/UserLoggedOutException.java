package com.proj1.exceptions;

public class UserLoggedOutException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserLoggedOutException() {
		super("You are currently logged out of the system.\n\nPlease log in to perform this action.");
	}
}

