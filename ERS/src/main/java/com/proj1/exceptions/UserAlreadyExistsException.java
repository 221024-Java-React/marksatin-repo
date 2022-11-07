package com.proj1.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	public UserAlreadyExistsException() {
		super("This user already exists.");
	}
	
}
