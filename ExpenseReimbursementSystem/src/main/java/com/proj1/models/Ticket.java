package com.proj1.models;

public class Ticket{
	
	private int ticketId;
	private int userId;
	private float amount;
	private String description;
	private ExpenseType type;
	private TicketStatus status;
	private int resolverId;
	
	public Ticket() {
		super();
	}
	
	public Ticket(int ticketId, int userId, float amount, String description, ExpenseType type, TicketStatus status, int resolverId) {
		super();
		this.ticketId = ticketId;
		this.userId = userId;
		this.amount = amount;
		this.description = description;
		this.type = type;
		this.status = status;
		this.resolverId = resolverId;
	}
	
	public Ticket(float amount, String description, ExpenseType type) {
		this.amount = amount;
		this.description = description;
		this.type = type;
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

	public ExpenseType getType() {
		return type;
	}

	public void setType(ExpenseType type) {
		this.type = type;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", userId=" + userId + ", amount=" + amount + ", description="
				+ description + ", type=" + type + ", status=" + status + ", resolverId=" + resolverId + "]";
	}
}
