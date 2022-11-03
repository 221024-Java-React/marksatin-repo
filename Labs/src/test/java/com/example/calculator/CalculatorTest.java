package com.example.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorTest {

	/* RED-GREEN TESTING
	 * 
	 * 1. Write a test - it will fail
	 * 2. Refactor - run the test again - it will pass
	 * 3. Rinse, Repeat
	 */
	
	private static Calculator calc;
	
	@BeforeClass
	public static void setUpNewCalculator() {
		System.out.println("This method would run once before any of the tests.");
		calc = new Calculator();
	}
	
	@Before
	public void setUpBeforeEachTest() {
		System.out.println("This will run before each test.");
	}
	
	@After
	public void tearDownAfterEachTest() {
		System.out.println("This will run after each test.");
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("This will run after the entire test suite.");
	}
	
	@Test
	public void testAddition1() {
		// We expect this to be 2
		int actual = calc.add(1, 1);
		
		// Let's test our expected output
		// message, expected output, actual output
		assertEquals("1 + 1 = 2", 2, actual);
	}
	
	@Test
	public void testAddition2() {
		int actual = calc.add(2,  1);
		
		assertEquals("2 + 1 = 3", 3, actual);
	}
	
	// We can test for exceptions in Junit
	// We do not need to assert anything because we are testing for the exception to be thrown
	// If this ArithmeticException is not thrown then the test will fail
	@Test(expected = ArithmeticException.class)
	public void testDivideByZero() {
		int test = calc.divide(1, 0);
	}
}
