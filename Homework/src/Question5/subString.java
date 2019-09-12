package Question5;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Scanner;
/**
 * Write a substring method that accepts a string str and an integer idx and returns the 
 * substring  contained  between  0  and  idx-1 inclusive.Do  NOT  use  any  of  the  existing 
 * substring methods in the String, StringBuilder, or StringBuffer APIs.
 * @author Don't Melt The CPU
 *
 */
public class subString {
	/**
	 * takes in user input
	 * @param args
	 */
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
	/**
	 * substring method.
	 * converts input string into char array
	 * prints from idx to end
	 * @param str
	 * @param idx
	 * @return
	 */
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
