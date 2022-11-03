package labs_1_5;

public class Lab4 {
	int[] numbers = new int[100];
	int evenSum = 0;
	int oddSum = 0;
	
	
	public static void main(String[] args) {
		Lab4 l4 = new Lab4();
		int j = 0;
		
		for (int i = 0; i < l4.numbers.length; i++) {
			l4.numbers[i] = i + 1;
		}
		
		while (j < l4.numbers.length) {
			if (l4.numbers[j] % 2 == 0) {
				l4.evenSum += l4.numbers[j];
			} else {
				l4.oddSum+= l4.numbers[j];
			}
			j++;
		}
		
		System.out.println(l4.evenSum);
		System.out.println(l4.oddSum);
	}
}
