package com.proj1.exceptions;

public class LoggedOutException extends RuntimeException {
	
	public LoggedOutException() {
		super("No user is currently logged in.");
	}
}

