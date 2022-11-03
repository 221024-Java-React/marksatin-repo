package lab9;

public class Lab9 {

	public int[] calculateBonuses(char... charGrades) {
		int[] bonusResults = new int[charGrades.length];
		int i = 0;
				
		do {
			char grade = charGrades[i];
			
			switch (grade) {
				case 'A':
					bonusResults[i] = 5000;
					break;
				case 'B':
					bonusResults[i] = 2500;
					break;
				case 'C':
					bonusResults[i] = 1000;
					break;
				case 'D':
					bonusResults[i] = 500;
					break;
				case 'F':
					bonusResults[i] = 0;
					break;
			}
			i++;
		} while (i < charGrades.length);
		return bonusResults;
	}
	
	public static void main(String[] args) {
		Lab9 l9 = new Lab9();
		
		char[] charGrades = new char[]{'A', 'B', 'B', 'F', 'D'};

		for (int bonus : l9.calculateBonuses(charGrades)) {
			System.out.println(bonus);			
		}
		
	}

}
