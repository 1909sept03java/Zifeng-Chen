package Question8;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
public class Palindromes {
	static ArrayList<String> palindrome = new ArrayList<String>();
	public static void main(String[] args) {
		Test();
		ArrayList<String> store= new ArrayList<String>();
		store.add("karen");
		store.add("madam");
		store.add("tom");
		store.add("civic");
		store.add("radar");
		store.add("sexes");
		store.add("jimmy");
		store.add("kayak");
		store.add("john");
		store.add("refer");
		store.add("billy");
		store.add("did");
		System.out.println("The Following String are stored into an ArrayList: ");
		for(String s : store)
			System.out.print(s + " ");
		addToPalindrome(store);
		System.out.println("\n\nThe Following Strings are saved into another ArrayList for Palindromes: ");
		for(String s : palindrome)
			System.out.print(s + " ");
	}
	@Test
	public static void Test()
	{
		assertEquals(isPalindrome("radar"),true);
		assertEquals(isPalindrome("car"),false);
	}
	public static boolean isPalindrome(String in)
	{
		int length;
		length = in.length();
		String reverse = "";
		for(int i = length-1;i >=0;i--)
			reverse = reverse + in.charAt(i);
		if(in.equals(reverse))
			return true;
		else
			return false;
	}
	public static void addToPalindrome(ArrayList<String> in)
	{

		for(String e : in)
		{
			if(isPalindrome(e))
				palindrome.add(e);
		}
		
	}
}
