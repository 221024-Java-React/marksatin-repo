package lab6;

public abstract class Customer {
	public String name;
	public double balance;
	public String items;
	public double cartCost;
	
	// Constructor for this class - all args
	public Customer(String name, double balance, String items, double cartCost) {
		this.name = name;
		this.balance = balance;
		this.items = items;
		this.cartCost = cartCost;
	};
	
	public void addToCart(String item, double cost) {
		cartCost += cost;
		items += " " + item;
	}
	
	public abstract void buy();	
}
