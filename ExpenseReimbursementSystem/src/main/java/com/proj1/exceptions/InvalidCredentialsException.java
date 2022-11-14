package com.proj1.exceptions;

public class InvalidCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException() {
		super("The Username or Password do not match those listed in the ERS user registry.\n\nPlease try again.");
	}
}
