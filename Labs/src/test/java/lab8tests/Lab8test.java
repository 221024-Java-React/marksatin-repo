package lab8tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import lab8.Lab8;

public class Lab8test {

	private static Lab8 l8;
	
	@BeforeClass
	public static void setUpNewLab8() {
		l8 = new Lab8();		
	}
	
	// Will PASS
	@Test
	public void lab8Test1() {
		String actual = l8.reverse("HelloBen");
		
		assertEquals("HelloBen reversed is neBolleH", "neBolleH", actual);
	}
	
	// Will PASS
	@Test
	public void lab8Test2() {
		StringBuffer sb = new StringBuffer("HelloBen");
		
		String actual = l8.reverse("HelloBen");
		String expected = sb.reverse().toString();
		
		assertEquals("l8.reverse() is same as StringBuffer.reverse().toString()", expected, actual);
	}
	
	// Will FAIL
	@Test
	public void lab8Test3() {
		StringBuffer sb = new StringBuffer("HelloBen");
		
		String actual = l8.reverse("HelloBen");
		StringBuffer expected = sb.reverse();
		
		assertEquals("l8.reverse() is same as StringBuffer.reverse()", expected, actual);
	}
	
	// Will FAIL
	@Test
	public void lab8Test4() {
		String actual = l8.reverse("123456789");
		
		assertEquals("Reversed String of numbers is same as reversed long", 987654321, actual);
	}
}
