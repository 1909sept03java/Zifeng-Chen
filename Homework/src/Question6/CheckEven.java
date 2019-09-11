package Question6;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class CheckEven {
	public static void main(String[] args)
	{
		Test();
		Scanner scan = new Scanner(System.in);
		System.out.println("Is this Even?\nPlease Enter a number: ");
		String num = scan.nextLine();
		int in;
		try {
			in = Integer.parseInt(num);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Please enter a number, Default value set to 69");
			in = 69;
		}
		System.out.println(in + " is even? " + isEven(in));
	}
	
	@Test
	public static void Test()
	{
		assertEquals(isEven(69),false);
		assertEquals(isEven(68),true);
	}
	public static Boolean isEven(int in)
	{
		if((in & 1) == 1)
			return false;
		else
			return true;
	}
}
