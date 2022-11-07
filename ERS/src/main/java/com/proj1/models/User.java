package com.proj1.models;

public class User {
	
	private int id;
	private CompanyRole role;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	public User() {
		super();
	}

	public User(int id, CompanyRole role, String firstName, String lastName, String email, String password) {
		super();
		this.id = id;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User(CompanyRole role, String firstName, String lastName, String email, String password) {
		super();
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CompanyRole getRole() {
		return role;
	}

	public void setRole(CompanyRole role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + "]";
	}
}
