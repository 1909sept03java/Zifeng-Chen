package Question12;
/**
 * Write a program to store numbers from 1 to 100 in an array. Print out all the even 
 * numbers from the array. Use the enhanced FOR loop for printing out the numbers
 * @author Zifeng CHen
 *
 */
public class enhancedForLoop {
	/**
	 * the main uses the enhanced for each loop to print variable j
	 * @param ars
	 */
	public static void main(String[] ars)
	{
		int[]store = new int[100];
		for(int i= 0; i <100; i++)
			store[i]= i+1;
		for(int j :store)
			if(j%2 == 0)	
				System.out.println(j + " ");
	}
}
