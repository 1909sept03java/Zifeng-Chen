package Question14;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class switchCase {
	public static void main(String[] args)
	{
		System.out.println("Testing Case Functionalities\nEnter numbers 1-3, others to quit\n1) Find the square root"
				+ "\n2) Display today's date\n3) Split the String, and store it");
		Scanner scan = new Scanner(System.in);
		String in = scan.nextLine();
		in = in.toLowerCase();
		switch (in)
		{
			case "1":
				Testsqrt();
				System.out.println("Please enter a number: ");
				String snum = scan.nextLine();
				double inum = 6.9;
				try {
					inum = Double.parseDouble(snum);
				}
				catch (NumberFormatException e)
				{
					System.out.println("Please enter a number, default set to 6.9");
				}
				inum = Math.sqrt(inum);
				System.out.println("This square root is: " + inum);
				break;
			case "2":
				SimpleDateFormat dateFormat = new SimpleDateFormat("K:mma MM/dd/yyyy");
				GregorianCalendar FthePope = new GregorianCalendar();
				String newDateFormat = dateFormat.format(FthePope.getTime());
				System.out.println(newDateFormat);
				break;
			case "3":
				Testsplit();
				
				String str = "I am learning Core Java";
				String[] spl = str.split(" ");
				for(String s: spl)
					System.out.println(s);
				break;
			default:
				
		}
		scan.close();
	}
	@Test
	public static void Testsqrt()
	{
		double a = 16.0;
		double b = 4.0;
		assertEquals(Math.sqrt(a),b,.00001);
		System.out.println("Test successful, printing");
	}
	@Test
	public static void Testsplit()
	{
		String[] sp = {"I","am","learning","Core","Java"};
		String str = "I am learning Core Java";
		assertTrue(Arrays.equals(str.split(" "), sp));
		System.out.println("Test successful, printing");
	}
}
