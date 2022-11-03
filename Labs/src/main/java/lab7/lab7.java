package lab7;

import lab6.PremiumCustomer;

public class lab7 {

	public static void main(String[] args) {
		PremiumCustomer pc1 = new PremiumCustomer("Mark Satin", 500.00, "Nintendo Switch, PS5, Big Screen TV", 100.00, 11874, 8);
		String origCart = pc1.getItems();
		double origCartCost = pc1.getCartCost();
				
		try {			
			pc1.setItems("Computer, Mouse, Keyboard");
			pc1.setCartCost(200.00);
			pc1.buy();	
			System.out.println("Try block");
		} catch (OverBalanceException e) {
			System.out.println("Catch block.");
			pc1.setItems(origCart);
			pc1.setCartCost(origCartCost);
		}
		
		System.out.println(pc1);
	}
}
