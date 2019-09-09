package Week1Challenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FindMutation {
	static ArrayList<String>bank = new ArrayList<String>();
	static String Start,End;
	static String[] bankstore;
	public static void main(String[]args) throws Exception
	{
			load();

			String[] bank1 = new String[] {"AACCGGTA"};
			System.out.println("Hard Code Test\nStart:AACCGGTT\nEnd:AACCGGTA\nBank:AACCGGTA");
			System.out.println(minimumMutation("AACCGGTT","AACCGGTA",bank1));
			System.out.print("\nRead From File (bank.txt)\nStart: "+Start+"\nEnd: "+End+"\nBank: ");
			for(String s : bankstore) System.out.print(s + " ");
			System.out.println("\n" + minimumMutation(Start,End,bankstore));
	}
	public static void load()
	{
		StringBuilder sb = new StringBuilder();
		File file =  new File("src/Week1Challenge/bank.txt");
		
		try {
			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {

				bank.add(line);

				line = br.readLine();
			}
			
		} catch (IOException e) {
			System.out.println("No Bank Found, program will fail");
			
		}
		Start = bank.get(0);
		End = bank.get(1);
		String temp = bank.get(2);
		bankstore = temp.split(",");
		
	}

	public static int minimumMutation(String Start, String End, String[] bank) {
		int count = 0; //init counter value

		
		//block for creating bankset, a set for the list of bank
		//bankset values will be add/remove. A set will deny dups enteries
		Set<String> bankset = new HashSet<>(); 
		for (String s : bank)
			bankset.add(s);
		
		
		//If End isn't in the bank, the end can't be reached
		if (!bankset.contains(End))
			return -1; 
		
		//check for equality of Start and End
		if (Start.equals(End)) 
			return 0;
		
		//Create a queue 'q' that those all the possible permutations of mutations of the start string
		// Sets up a Queue for FiFo so each generations will be test sequentially
		Queue<String> q = new LinkedList<String>();
		q.add(Start); //first entry is the start
		
		
		//Create list of char for possible mutations 
		char[] checker = new char[] { 'A', 'T', 'G', 'C' };
		
		//Runs in a loop until the queue is empty or end if found 
		//While loops block runs each generation per loop 
		while (!q.isEmpty()) {
			
			//each permutation of the generation per for loop
			for (int permGene= q.size(); permGene > 0; permGene--) {
				
				//removes the "ith" (permGene) of the (q) queue and stores in string
				String temp = q.remove();

				//if we found it
				if (temp.equals(End))
					return count;
				
				//inner for loop creates the next generation of the String and pushing into queue
				//this is done have creating every possible permutations of the create "gene"
				//and the matching the bank, and inserting into the queue if matches
				
				//for loop runs per char of the 8 character string. 
				//logically runs 8 times, but can be scaled 
				for (int posChar = 0; posChar < temp.length(); posChar++) {
					//turn your immutable String into list of char, to do farer ops
					char[] proc = temp.toCharArray();
					//for each loop that creates and checks the next generation
					//At the current "posChar", posistion of the char list we are editing
				
					for (char c : checker)
					{
						//the char list is changed to each of the 4 chars in checker
						//this is create a dup of the original 
						proc[posChar] = c;
						//turn char list into String
						String possibleMutate = new String(proc);
						//check if the new mutate is part of the bank, and it is not the original dup
						if(bankset.contains(possibleMutate) && !possibleMutate.equals(temp)) {
							//removes the that instance of mutations from the set to prevent backtracking
							bankset.remove(possibleMutate);
							//inserts the next generation into the queue
							q.add(possibleMutate);
						}
					}
				}
			}
			//one generation run
			count ++;
		}
		//if we fail to find the end return -1
		return -1;
	}

}
