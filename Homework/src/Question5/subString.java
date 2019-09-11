package Question5;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class subString {

	public static void main(String[] args) {
		Test();
		Scanner scan = new Scanner(System.in);
		String str;
		String id;
		System.out.println("SubString\nPlease enter String: ");
		str = scan.nextLine();
		System.out.println("Please enter range");
		id = scan.nextLine();
		int idx;
		try
		{
			idx = Integer.parseInt(id);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Enter enter valid number, Default will be set to string length");
			idx = str.length();
		}
		if(idx > str.length()) {
			System.out.println("This is byond the max length, will set to max: "+ str.length());
			idx = str.length();
		}
		System.out.println("Output: " + sub(str,idx));
	}
	@Test
	public static void Test()
	{
		String str = "This is a String";
		int idx = 4 ;
		assertEquals(sub(str,idx),"This");
	}
	public static String sub(String str, int idx)
	{
		char[] temp = str.toCharArray();
		String out = "";
		for(int i = 0; i < idx;i++)
		{
			out = out + String.valueOf(temp[i]);
		}

		return out;
	}
}
