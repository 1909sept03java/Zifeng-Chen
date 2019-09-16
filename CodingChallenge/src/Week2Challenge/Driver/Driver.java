package Week2Challenge.Driver;

import java.util.Scanner;

import Week2Challenge.DAO.*;
import Week2Challenge.beans.Employee;

public class Driver {

	public static void main(String[] args) {
		boolean debug = true;
		
		EmployeeDaoImple ee = new EmployeeDaoImple();
		if (debug) {
			System.out.print("Welcome to Employee Driver\n");
				
			Scanner scan = new Scanner(System.in);
			String input,input2,input3;
			char con;
			boolean run = true;
			while (run) {
				System.out.print("\nA)Show All Users\nB)Give Raise of 10% to Department ID\n"
					+ "C)Give custom raise to Department ID\nQ)Quit\nEnter:\t");
				input = scan.nextLine();
				if (input.equals(""))
					input = "dddd";
				con = input.toUpperCase().charAt(0);
				switch (con) {
				case 'A':
					for (Employee e : ee.getEmployee())
						System.out.println(e.toString());
					break;
				case 'B':
					System.out.println("Please enter an ID (Number 10s)");
					input2 = scan.nextLine();
					
					try {
						int id = Integer.parseInt(input2);
						ee.giveRaise(id);
					}
					catch(NumberFormatException e)
					{
						System.out.println("Please enter a number");
					}
					break;
				case 'C':
					System.out.println("Please enter an ID (Number 10s)");
					input2 = scan.nextLine();
					System.out.println("Please enter raise amount (double, 1.0 is no raise)");
					input3 = scan.nextLine();
					try {
						int id = Integer.parseInt(input2);
						double raise = Double.parseDouble(input3);
						if (raise < 1) {
							System.out.println("What are you doing? This is for a RAISE! Setting raise to 1");
							raise = 1;
						}
						ee.giveRaise(id,raise);
					}
					catch(NumberFormatException e)
					{
						System.out.println("Please enter a number");
					}
					break;
				case 'Q':
					System.out.println("Quitting");
					run = false;
				default:
					System.out.println("Wrong input");
				}
			}

		} else {
			
			for (Employee e : ee.getEmployee())
				System.out.println(e.toString());

		}
	}

}
