package com.proj1.exceptions;

public class InvalidCredentialsException extends RuntimeException {

	public InvalidCredentialsException() {
		super("Username and Password do not match those listed in database.");
	}
}
