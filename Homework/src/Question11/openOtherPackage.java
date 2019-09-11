package Question11;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Question11_OtherPackage.otherPackage;
public class openOtherPackage {

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
