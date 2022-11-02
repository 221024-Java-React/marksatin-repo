package lab6;

import lab7.OverBalanceException;

public class PremiumCustomer extends Customer implements Premium {
	private int vipCard;
	private int years;

	// Constructor of this class - all args
	public PremiumCustomer(String name, double balance, String items, double cartCost, int vipCard, int years) {
		// Constructor elements passed in from Customer superclass
		super(name, balance, items, cartCost);
		this.vipCard = vipCard;
		this.years = years;	
	}

	public double discountPrice(double cartCost) {
		double discountedCartPrice = .85*cartCost;
		return discountedCartPrice;
	}
	
	public void buy() {
//		items = "";
//		cartCost = 0.0;
//		balance -= discountedCartPrice;
		double discountedCartPrice = discountPrice(cartCost);
		
		System.out.println(balance);
		System.out.println(discountedCartPrice);
		
		if (balance < discountedCartPrice) {
			throw new OverBalanceException();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double newBalance) {
		this.balance = newBalance;
	}
	
	public String getItems() {
		return items;
	}
	
	public void setItems(String newItems) {
		this.items = newItems;
	}
	
	public double getCartCost() {
		return cartCost;
	}
	
	public void setCartCost(double newCartCost) {
		this.cartCost = newCartCost;
	}
	
	public int getVipCard() {
		return vipCard;
	}
	
	public void setVipCard(int newVipCard) {
		this.vipCard = newVipCard;
	}
	
	public int getYears() {
		return years;
	}
	
	public void setYears(int newYears) {
		this.years = newYears;
	}

	public String toString() {
		return name + " " + balance + " " + items + " " + cartCost + " " + TITLE + " " + vipCard + " " + years; 
	}
}
