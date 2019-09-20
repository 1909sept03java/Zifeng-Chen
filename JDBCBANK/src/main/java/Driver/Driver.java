package Driver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import dao.UserControlDaoimplement;
import util.ConnectionUtil;

public class Driver {
/**
 * The Main driver of project JDBC bank
 * @author Zifeng Chen
 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean run = true;
		boolean inner_run = false;
		String input;
		String un, pw;
		UserControlDaoimplement e = new UserControlDaoimplement();
		e.getUser();
		//outer while loop operates the log in screen
		// will take 3 commands, login, reate, and quit
		
		while (run) {
			System.out.print("Welcome to the Bank!" + "\nL) Login\nN) Create New User\nQ) Quit\nCommand:\t");
			input = scan.nextLine();
			char i;
			if (input == null)
				i = ' ';
			else
				i = input.toLowerCase().charAt(0);
			if (i == 'q') {
				run = false;
				break;
			} else if (i == 'n') {
				System.out.print("Creating New User\nEnter Username:\t");
				un = scan.nextLine();
				System.out.print("Enter Password:\t");
				pw = scan.nextLine();
				if (un == null || pw == null)
					System.out.println("Invalid inputs");
				else
					e.create(un, pw);
				e.getUser();

			} else if (i == 'l') {
				System.out.print("Enter Username:\t");
				un = scan.nextLine();
				System.out.print("Enter Password:\t");
				pw = scan.nextLine();
				if (un == null || pw == null)
					System.out.println("Invalid inputs");
				else {
					if (e.login(un, pw)) {
						inner_run = true;
						boolean priv = e.getPriv();
						System.out.println("\nWelcome " + e.getCurrentUser() + " To the Bank of BAD UX"
								+ "\nQ) Log out\nA) Show all personal accounts\nC) Create new account\nE) Remove all empty accounts\n"
								+ "D) Deposit into account\nW) Withdraw from account\nH) Show history");
						if (priv)
							System.out.println("Admin detected\nS) Show Everything\nU) Delete User\nN) Create New User");
						while (inner_run) {
							System.out.print("\nYour Commands:\t");
							input = scan.nextLine();
							char c;
							if (input == null)
								c = ' ';
							else
								c = (input.toLowerCase().charAt(0));

							
							if (c == 'q') {
								System.out.println("Logging out");
								inner_run = false;
								e.logout();
							}
							else if(c=='n' && e.getPriv()==true) {
								System.out.print("Creating a new user\nEnter Username:\t");
								String un1 = scan.nextLine();
								System.out.print("Enter Password:\t");
								String pw1 = scan.nextLine();
								if(un1 == null || pw1 == null) {
									System.out.println("Invalid input!");
								}
								else {
									e.create(un1, pw1);
									e.getUser();
									e.login(un, pw);
									System.out.println("New User Made");
								}
								
							}
							else if(c=='u'&& e.getPriv()==true) {
								System.out.println("Deleting an user\nPlease User ID:\t");
								String str = scan.nextLine();
								try {
									int id = Integer.parseInt(str);
									e.deleteUser(id);
									e.getUser();
									e.login(un, pw);
								}
								catch(NumberFormatException e2)
								{
									System.out.println("Invalid input");
								}
							}
							// show all user is admin func
							else if (c == 's'&& e.getPriv()==true ) {
								e.showAllUsers();
								System.out.println("Show all user");
								
							}
							// show all my accounts
							else if (c == 'a') {

								System.out.println("show all my accounts");
								e.showAccount();
							}
							// create new account
							else if (c == 'c') {
							//	System.out.println("1");
								e.createNewAccount();
								//System.out.println("2");
								e.getUser();
							//	System.out.println("3");
								e.login(un, pw);
								System.out.println("New Account Created!");
							}
							// deposit into my account
							else if (c == 'd') {
								System.out.println("Enter the account you want to deposit into");
								String strId = scan.nextLine();
								System.out.println("Enter a positive amount to deposit");
								String strAmount = scan.nextLine();
								int id;
								double amount;
								try {
									id = Integer.parseInt(strId);
									amount = Double.parseDouble(strAmount);
									if (amount <= 0)
										System.out.println("Positive Inputs only");
									else
										e.changeBalance(id, amount);
								} catch (NumberFormatException e1) {
									System.out.println("Wrong inputs");
								}

							}
							// delete empty accounts
							else if (c == 'e') {
								System.out.println("Deleting all empty accounts of User");
								e.clearEmptyAccount();
								e.getUser();
								e.login(un, pw);
							}
							// withdraw
							else if (c == 'w') {
								System.out.println("Enter the account you want to withdraw into");
								String strId = scan.nextLine();
								System.out.println("Enter a positive amount to withdraw");
								String strAmount = scan.nextLine();
								int id;
								double amount;
								try {
									id = Integer.parseInt(strId);
									amount = Double.parseDouble(strAmount);
									if (amount <= 0)
										System.out.println("Positive Inputs only");
									else
										e.changeBalance(id, -amount);
								} catch (NumberFormatException e1) {
									System.out.println("Wrong inputs");
								}

							} else if (c == 'h') {
								System.out.println(
										"Show transaction history of account\nPlease enter account id you want to see history of");
								String strId = scan.nextLine();
								try {
									int id = Integer.parseInt(strId);

									e.showTransaction(id);

								} catch (NumberFormatException e3) {
									System.out.println("Wrong inputs");
								}
							} else {
								System.out.println("Try input again");
							}
						}

					} else
						System.out.println("No user found");
				}
			} else {
				System.out.println("Invalid input");
			}
			// inner loop

		} /*
			 * System.out.println("main loop\nHello " + e.getCurrentUser()
			 * +"\nEnter in command: "); input = scan.nextLine();
			 * 
			 * 
			 * }
			 */
	}

}
