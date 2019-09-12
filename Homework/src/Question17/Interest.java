package Question17;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
/**
 * Write a program that calculates the simple interest on the principal, rate of interest 
 * and  number  of  years  provided  by  the  user.  Enter  principal,  rate  and  time  through  the 
 * console using the Scanner class
 * @author Zifeng Chen
 *
 */
public class Interest {
/**
 * Main takes in 3 user input
 * the 3 input types must be doubles
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test();
		Scanner scan = new Scanner(System.in);
		System.out.print("Calculating Interest\nPease enter principal:\n");
		double principle, rate, time;
		principle = scan.nextDouble();
		System.out.println("Please enter rate");
		rate = scan.nextDouble();
		System.out.println("Please enter time");
		time = scan.nextDouble();
		scan.close();
		double interest = calculateInterest(principle,rate,time);
		System.out.println("\nYour Principle: " + principle+"\nYour Rate: " + rate + "\nYour time: " + time + "\nInterest: " +interest );
		

	}
/**
 * simple multiplication
 * @param principle
 * @param rate
 * @param time
 * @return
 */
	public static double calculateInterest(double principle, double rate, double time) {
		return principle * rate * time;
	}
	
	/**
	 * 1*2*3 = 6 test
	 */
	@Test
	public static void Test()
	{
		assertEquals(calculateInterest(1,2,3),6,.0001);
	//	System.out.println("Test succe");
	}
}
