package Question13;

import java.util.Scanner;

public class makeTriangle {

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
