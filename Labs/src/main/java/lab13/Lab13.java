package lab13;

public class Lab13 {

	public String fizzBuzz(int N) {
		if (N % 3 == 0 && N % 5 == 0) {
			return "FizzBuzz";
		} else if (N % 3 == 0) {
			return "Fizz";
		} else if (N % 5 == 0) {
			return "Buzz";
		} else {
			String s = String.valueOf(N);
			return s;
		}
	}
	
	public static void main(String[] args) {
		Lab13 l13 = new Lab13();
		
		System.out.println(l13.fizzBuzz(31));
	}
};
