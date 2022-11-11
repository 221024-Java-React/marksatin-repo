package com.proj1.models;

public class Ticket{
	
	private int ticketId;
	private int userId;
	private float amount;
	private String description;
	private TicketStatus status;
	
	public Ticket() {
		super();
	}
	
	public Ticket(int ticketId, int userId, float amount, String description, TicketStatus status) {
		super();
		this.ticketId = ticketId;
		this.userId = userId;
		this.amount = amount;
		this.description = description;
		this.status = status;
	}
	
	public Ticket(int userId, float amount, String description, TicketStatus status) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.description = description;
		this.status = status;
	}

	public Ticket(float amount, String description, TicketStatus status) {
		super();
		this.amount = amount;
		this.description = description;
		this.status = status;
	}
	
	public Ticket(float amount, String description) {
		super();
		this.amount = amount;
		this.description = description;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	public int getUserId() {
		return ticketId;
	}

	public void setUserId(int ticketId) {
		this.ticketId = ticketId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", userId=" + userId + ", amount=" + amount + ", description="
				+ description + ", status=" + status + "]";
	}
}
