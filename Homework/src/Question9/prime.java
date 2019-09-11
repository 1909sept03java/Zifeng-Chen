package Question9;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
public class prime {

	public static void main(String[] args) {
		Test();
		ArrayList<Integer> num = new ArrayList<Integer>();
		for(int i = 1; i <= 100; i ++) 
			num.add(i);
		System.out.println("Check for Prime Number from 1 to 100:");
		for(int i : num)
			if( isPrime(i))
				System.out.print(i  + " ");

			
	}
	@Test
	public static void Test()
	{
		assertEquals( isPrime(5),true);
		assertEquals( isPrime(6),false);
	}
	public static boolean isPrime(int in)
	{
		boolean flag = false;
		for(int i = 2 ; i <= in/2 ;i++)
			if(in%i == 0)
				flag = true;
		return !flag;
		
	}
}
