package Week1Challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Mutate {
	static ArrayList<String> bank = new ArrayList<String>();
	public Mutate()
	{
	
	}
	public static int gen(String in)
	{
		return 0;
	}
	public static void load()
	{
		
		try {
			FileReader file = new FileReader("bank.txt");
			BufferedReader br = new BufferedReader(file);
			String line = br.readLine();
			while (line != null) {
				if(line.toCharArray().length==8)
				{
				bank.add(line);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("No Bank Found, program will fail");
		}
		
		
		
		
	}
}
