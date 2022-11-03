package lab8;

public class Lab8 {

	public String reverse(String input) {
		StringBuffer output = new StringBuffer(input);
		
		for (int i = input.length() - 1; i >= 0; i--) {
			output.append(input.charAt(i));
			output.delete(0, 1);
		}
		return output.toString();
	}
	
	public static void main(String[] args) {
		Lab8 l8 = new Lab8();
				
		String revString = l8.reverse("HelloMark");
		System.out.println(revString);
	}
}
