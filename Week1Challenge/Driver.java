package Week1Challenge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {

	public static void main(String[]args) throws Exception
	{
		Mutate.load();
		for(String e: Mutate.bank)
		{
			System.out.println(e);
		}
	}
	
}
