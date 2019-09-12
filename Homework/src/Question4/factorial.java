package Question4;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.IllegalFormatException;
import java.util.Scanner;
/**
 * Write a program to compute N factorial
 * @author Zifeng Chen
 *
 */
public class factorial {

	public static void main(String[] args) {
		Test();
		System.out.println("N Factorial Calculator\nPlease Enter number: ");
		Scanner scan = new Scanner(System.in);
		String in = scan.nextLine();
		int proc = 5;
		try
		{
			proc = Integer.parseInt(in);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Please enter a number, The default input will be set to (5)");
		}
		System.out.println("Input: " + proc + "\nFactorial: "+fact(proc));
	}
	@Test
	public static void Test()
	{
		assertEquals(fact(5),120);
		
	}
	/**
	 * Performs factorial
	 * @param in
	 * @return
	 */
	public static int fact(int in)
	{
		if (in == 0) return 0;
		int counter = in;
		for(int i = in-1; i > 0; i--)
		{
			counter *= i;
		}
		return counter;
	}
}
