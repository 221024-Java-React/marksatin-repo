package lab9tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import lab9.Lab9;

public class Lab9Test {

	private static Lab9 l9;
	private char[] charArray = new char[]{'A', 'B', 'C', 'D', 'F'};
	private char[] wrongCharsArr = new char[] {'Z', 'P', 'E', 'L'};
	ArrayList<Integer> actualList = new ArrayList<Integer>();

	
	@BeforeClass
	public static void setUpNewLab9() {
		l9 = new Lab9();
	}
	
	// Will PASS
	@Test
	public void lab9Test1() {
		ArrayList<Integer> expectedList = new ArrayList<Integer>(List.of(5000, 2500, 1000, 500, 0));
		
		for (int i : l9.calculateBonuses(charArray)) {
			actualList.add(i);
		}
		
		assertEquals("chars match correct int values", expectedList, actualList);
	}
	
	// Will PASS
	@Test
	public void lab9Test2() {
	ArrayList<Integer> expectedList = new ArrayList<Integer>(List.of(0, 0, 0, 0));
		
		for (int i : l9.calculateBonuses(wrongCharsArr)) {
			actualList.add(i);
		}
		
		assertEquals("chars match correct int values", expectedList, actualList);
	}
	
	// Will FAIL
	@Test
	public void lab9Test3() {
		int[] intArray = l9.calculateBonuses(charArray);
		
		assertEquals("char array input and int array output are same object", charArray, intArray);
	}
	
	// Will FAIL
	@Test
	public void lab9Test4() {
		char[] emptyCharArr = new char[5];
		
		for (int i : l9.calculateBonuses(emptyCharArr)) {
			actualList.add(i);
		}
		
		String expectedException = "The array is empty and the calculateBonuses() method cannot be executed.";
		
		System.out.println(actualList);
		assertEquals("an empty char away will throw an error", expectedException, emptyCharArr);
	}
}
