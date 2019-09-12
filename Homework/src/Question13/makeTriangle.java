package Question13;

import java.util.Scanner;
/**
 * Display the triangle on the console as follows using any type of loop.
 * Do NOTuse a simple group of print statementsto accomplish this
 * @author Zifeng CHen
 *
 */
public class makeTriangle {
/**
 * main take user input for height of pyramid
 * @param args
 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Number Pyramid\nEnter height: ");
		String in = scan.nextLine();
		scan.close();
		int height;
		try {
			height = Integer.parseInt(in);
		}
		catch (NumberFormatException e)
		{
			System.out.println("Please Enter number, Default will set to 5");
			height = 5;
		}
		triangle(height);

	}
	/**
	 * uses a flag and nested for loops to print out the tower
	 * @param height : tower height
	 */
	public static void triangle(int height)
	{
		boolean flag = false;
		for(int i = 1; i <=height;i++)
		{
			for(int j = 0; j<i;j++)
			{
				if(flag) {
					System.out.print("1 ");
					flag = false;
				}
				else {
					System.out.print("0 ");
					flag = true;
				}
			}
			
			System.out.println("");
			
		}
		
	}
}
