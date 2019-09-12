package Question10;

import static org.junit.Assert.assertEquals;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
/**
 * Task:
 * Find the minimum of two numbers using ternary operators
 * @author Zifeng Chen
 */
	
public class minimum {
	/*
	 * 
	 * Main method requests for 2 user defined inputs
	 * and tries to parse then into int(s)
	 * upon failure the default values will be set as 6 and 9.
	 * 
	 */
	public static void main(String[] args) {
		Test();
		Scanner scan = new Scanner(System.in);
		System.out.println("Find Smaller Number with Ternary operator:\nPlease enter first number: ");
		String in1 = scan.nextLine();

		int num1, num2;

		try {
			num1 = Integer.parseInt(in1);
		} catch (NumberFormatException e) {
			System.out.println("Please enter a valid number, Default to 6");
			num1 = 6;
		}
		System.out.println("Please enter the Second Number: ");
		String in2 = scan.nextLine();
		scan.close();
		try {
			num2 = Integer.parseInt(in2);
		} catch (NumberFormatException e) {
			System.out.println("Please enter a valid number, Default to 9");
			num2 = 9;
		}
		int num3 = findSmallWithTernary(num1, num2);
		System.out.println("The smaller number between " + num1 + " and " + num2 + " is: " + num3);
	}
	/**
	 * The test method
	 * asserts equality between the 2 values, 
	 * manually edit test cases 
	 * current test checks between 6 and 9
	 */
	@Test
	public static void Test() {
		assertEquals(findSmallWithTernary(6, 9), 6);
	}
	/**
	 * requests 2 parameter of ints
	 * will not check object type
	 * performs 1 ternary operations and returns the smallest value
	 * @param a int inout 1
	 * @param b	int input 2
	 * @return smaller value of a and b, or the value of a if equal
	 */
	public static int findSmallWithTernary(int a, int b) {
		int result = (a < b) ? a : b;
		return result;
	}
}
