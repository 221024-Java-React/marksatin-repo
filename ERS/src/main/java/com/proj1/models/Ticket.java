package com.proj1.models;

public class Ticket{
	
	private int id;
	private float amount;
	private String description;
	private TicketStatus status;
	
	public Ticket() {
		super();
	}
	
	public Ticket(int id, float amount, String description, TicketStatus status) {
		super();
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "Ticket [id=" + id + ", amount=" + amount + ", description=" + description + ", status=" + status + "]";
	}
}
