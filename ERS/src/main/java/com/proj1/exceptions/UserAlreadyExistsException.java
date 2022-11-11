package com.proj1.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException() {
		super("You are already registered in ERS.\n\nPlease log in with your credentials to access your account.");
	}
	
}
