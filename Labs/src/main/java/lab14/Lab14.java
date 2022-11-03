package lab14;

public class Lab14 {
	
	public static String twoKidsAndATruck(int k1, int k2, int t) {
		int k1Dist = Math.abs(t - k1);
		int k2Dist = Math.abs(t - k2);
		
		if (k1Dist < k2Dist) {
			return "Kid 1";
		} else if (k2Dist < k1Dist) {
			return "Kid 2";
		} else {
			return "Truck";
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(Lab14.twoKidsAndATruck(9, 1, 5));
	}

}
