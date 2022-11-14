package com.proj1.exceptions;

public class UserLoggedInException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserLoggedInException() {
		super("Someone is already logged in to the system.\n\nPlease log in to perform this action.");
	}
}

