package Question10;

import static org.junit.Assert.assertEquals;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class minimum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

	@Test
	public static void Test() {
		assertEquals(findSmallWithTernary(6, 9), 6);
	}

	public static int findSmallWithTernary(int a, int b) {
		int result = (a < b) ? a : b;
		return result;
	}
}
