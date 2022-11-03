package labs_1_5;

public class Lab2 {
	
	int add(int a, int b) {
		return a + b;
	}
	
	int sub(int a, int b) {
		return a - b;
	}
	
	double multiply(double a, double b) {
		return a * b;
	}
	
	double divide(double a, double b) {
		return a / b;
	}
	
	public static void main(String[] args) {
		Lab2 l2 = new Lab2();
		
		l2.add(4, 5);
		l2.sub(8, 3);
		l2.multiply(4.8, 2.9);
		l2.divide(6.1, 3.8);
	}
}
