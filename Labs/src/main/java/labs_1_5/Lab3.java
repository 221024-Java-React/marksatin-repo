package labs_1_5;

public class Lab3 {
	
	public String replaceAllSpaces(String word) {
		word = word.replace(' ', '-');
		System.out.println(word);
		return word;
	}
	
	public boolean containsWord(String inputWord, String searchWord) {
		inputWord = inputWord.toLowerCase();
		
		if (inputWord.contains(searchWord)) {
			System.out.println("True");
			return true;
		} else {
			System.out.println("False");
			return false;
		}
	}
	
	public String[] splitPhoneNumber(String phoneNum) {
		String[] phoneNumArr = phoneNum.split("-");
		System.out.println(phoneNumArr[0] + phoneNumArr[1] + phoneNumArr[2]);
		return phoneNumArr;
	}
	
	public static void main(String[] args) {
		Lab3 l3 = new Lab3();
		
		l3.replaceAllSpaces("Hello there my friend!");
		l3.containsWord("MARK", "ak");
		l3.splitPhoneNumber("301-926-1307");
	}
}
