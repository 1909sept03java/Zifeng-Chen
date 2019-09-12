package Question11;
import static org.junit.Assert.assertEquals;
/**
 * Write  a  program  that  would  access  two  float-variables from a  class  that  exists  in 
 * another  package. Note,  you will need  to  create two  packages  to  demonstrate  the solution
 * @author Zifeng Chen
 */

import org.junit.jupiter.api.Test;

import Question11_OtherPackage.otherPackage;
public class openOtherPackage {
	/**
	 * THe main program will try to access a variable from another package called Question11_OtherPackage
	 * @param args
	 */
	public static void main(String[] args) {
		Test();
		otherPackage.otherInteger = 1;
		System.out.println("Accessing otherPackage's otherInteger: " + otherPackage.otherInteger);
		
	}
	@Test
	public static void Test()
	{
		otherPackage.otherString="Hello World";
		assertEquals(otherPackage.otherString,"Hello World");
	}
}
