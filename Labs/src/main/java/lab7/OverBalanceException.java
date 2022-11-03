package lab7;

public class OverBalanceException extends RuntimeException {

	public OverBalanceException() {
		super("You do not have enough money to purchase the items in your cart.");
	}
}
