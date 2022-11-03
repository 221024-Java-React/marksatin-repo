package lab9test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lab9.Lab9;

public class Lab9Test {

	private Lab9 l9test = new Lab9();
	private char[] charArray = new char[]{'A', 'B', 'B', 'F', 'D'};
	private int[] expectedArray = new int[]{5000, 2500, 2500, 0, 500};
	
	@Test
	public void testResultA() {
		int[] actualArray = l9test.calculateBonuses(charArray);
		
		assertEquals("dd", expectedArray, actualArray);
	}
}
