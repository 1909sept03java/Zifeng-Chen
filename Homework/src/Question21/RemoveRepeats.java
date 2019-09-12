package Question21;
import static org.junit.Assert.assertEquals;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
public class RemoveRepeats {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test();
		System.out.println(repeat("testesetesetsetestttts") + "\ntes");
	}
	public static String repeat(String in)
	{
		String out = "";
		Set<Character> charset = new LinkedHashSet<Character>();
		for(char c : in.toCharArray())
			charset.add(c);
		for(Character c: charset)
			out = out + String.valueOf(c);
		return out;
	}
	@Test
	public static void Test()
	{
		String tester = "tes";
		assertEquals(repeat("testesetesetsetestttts"),tester);
		System.out.println("Test success");
	}
}
