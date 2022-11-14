package com.proj1.exceptions;

public class UserRoleDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserRoleDoesNotExistException() {
		super("The User Role you entered does not exist.");
	}
}
