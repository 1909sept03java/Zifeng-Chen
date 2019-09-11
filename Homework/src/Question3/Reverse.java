package Question3;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class Reverse {

	public static void main(String[] args) {
		String in = "This is a String that will be reversed!";
		System.out.println(in);
		in = flip(in);
		System.out.println(in);
		Test();
	}


	@Test
	public static void Test()
	{
		String in = "This is a String that will be reversed!";
		String out = "!desrever eb lliw taht gnirtS a si sihT";
		assertEquals(flip(in),out);
	}
	
	
	public static String flip(String in)
	{
		for (int i = 0; i < in.length(); i++) {
		    in = in.substring(1, in.length() - i)
		        + in.substring(0, 1)
		        + in.substring(in.length() - i, in.length());
		 }
		 return in;
	}
}
