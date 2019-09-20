package dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.ConnectionUtil;
import bean.User;

public class UserControlDaoimplement implements UserControlDao {
	private ArrayList<User> us = new ArrayList<User>();
	private User currentUser = null;
	/**
	 * getCurrentUser return the username of the CurrentUser
	 * if there isn't, not logged in is the name
	 * @return
	 */
	public String getCurrentUser() {
		if (currentUser != null) {
			return currentUser.getUsername();
		} else
			return "Not Logged in";
	}
	/**
	 * getPriv returns the admin status of the current user
	 * @return
	 */
	public boolean getPriv() {
		return currentUser.isAdmin();
	}
	/**
	 * Slightly misname method, 
	 * This method gets all the user and inserts it into the User arraylist
	 * this method resets the arraylist us, and User currentUser
	 * uses the jdbc unit and called the sql command
	 * Select * From Bank_USER 
	 * returns all data from table BANK_USER and adds into arraylist
	 * The calls the sql command:
	 * SELECT * FROM BANK_ACCOUNT WHERE USER_ID = ?
	 * Pulls all entries from BANK_ACCOUNT and inserts the bank id and money into the USER
	 * ? is taken from USER
	 * 
	 * Warning: currentUser is reset, and login must be recalled 
	 * Warning2: getUser no longer auto login, and must login(un,pw) must be called as follow up
	 */
	@Override
	public void getUser() {
		us = null;
		us = new ArrayList<User>();
		currentUser = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = "Select * FROM BANK_USER";
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			while (rs.next()) {
				if( rs.getString("USER_NAME") != null &&rs.getString("PASSCODE")!=null )
				us.add(new User(rs.getInt("USER_ID"), rs.getInt("PERM"), rs.getString("USER_NAME"),
						rs.getString("PASSCODE")));
				//else
				//	System.out.println("Null account found,removed");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (User u : us) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				String sql = "SELECT * FROM BANK_ACCOUNT WHERE USER_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, u.getUserId());
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					u.setAccount(rs.getInt("BANK_ACCOUNT_ID"));
					u.setMoney(rs.getDouble("MONEY")); // name unknow
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}
	/**
	 * WARNING: This method no longer calls database
	 * 
	 * Login matches the 2 user input with all the USERs in the ArrayList us
	 * if a match is found currentUser is set to that USER and return true
	 * un and pw are stored scope only in order to for admin to create new
	 * user and still stay logged in , this implement resolves the issue 
	 * from the getUser changes
	 * 
	 */
	@Override
	public boolean login(String un, String pw) {
		for (User u : us) {
		//	System.out.println(u.toString());
			if (u.getUsername().equals(un) && u.getPasscode().equals(pw)) {
				currentUser = u;
				return true;

			}
		}
		return false;
	}
	/**
	 * log user out
	 * sets currentUser to null
	 * why make documentation for this?
	 */
	@Override
	public void logout() {
		currentUser = null;
	}
	/**
	 * Admin only method
	 * REDUNDANT: checks currentUSER for admin privs
	 * returns all the users in arraylist us.
	 * does not call database. 
	 * logical warning: can't be called before getUser();
	 */
	@Override
	public void showAllUsers() {
		if (currentUser.isAdmin()) {
			for (User s : us)
				System.out.println(s.toString());
		} else
			System.out.println("Not the Admin user, can't show all");
	}
	/**
	 * Shows the currentUser's all active bank account details
	 * Don't need db connection USERs store all information locally
	 * 
	 */
	@Override
	public void showAccount() {
		System.out.println("Your Accounts are: \nAccount ID   Balance");
		if (!currentUser.showAccount().isEmpty()) {

			for (int i = 0; i < currentUser.showAccount().size(); i++) {
				System.out.println(String.format("%-2d           %.2f", currentUser.showAccount().get(i),
						currentUser.showMoney().get(i)));
			}

		} else
			System.out.println("It is empty");
	}
	/**
	 * allows currentUser to change 1 account's balance
	 * with and depo both call this method thus amount can be negative
	 * logical check for sane input occurs elsewhere
	 * 
	 * checks currentUser if account exists, and uses procedures
	 * SP_TRANSACTION
	 * Note: SP_TRANSACTION updates the bank_account and insert Transactions
	 * 
	 * NOTE: sanity check for withdrawal more than account is checked here
	 */
	@Override
	public void changeBalance(int id, double amount) {
		int pos = -1;
		for (int i = 0; i < currentUser.showAccount().size(); i++) {
			if (currentUser.showAccount().get(i) == id)
				pos = i;
		}
		if (pos == -1) {
			System.out.println("No Account found");
		} else {
			if ((amount * -1) > currentUser.showMoney().get(pos)) {
				System.out.println("Withdraw too much money");
			} else {

				try (Connection conn = ConnectionUtil.getConnection()) {
					String pro = "{call SP_TRANSACTION(?, ?)}";
					CallableStatement po = conn.prepareCall(pro);
					po.setInt(1, id);
					po.setDouble(2, amount);
					boolean result = po.execute();
					currentUser.changeMoney(pos, amount);
					System.out.println("done");
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

		}
	}
	/**
	 * create new bank account in currentUser
	 * calls database
	 * uses procedures
	 * SP_INSERT_ACCOUNT(?)
	 * preconditions: currentUser is set
	 * 
	 */
	@Override
	public void createNewAccount() {
	//	System.out.println(currentUser.getUserId());
		try (Connection conn = ConnectionUtil.getConnection()) {
			String pro = "{call SP_INSERT_ACCOUNT(?)}";
			CallableStatement po = conn.prepareCall(pro);
			po.setInt(1, currentUser.getUserId());
			boolean result = po.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * searches currentUser's all bank account,
	 * when money is 0 sql command
	 * DELETE  FROM BANK_ACCOUNT WHERE BANK_ACCOUNT_ID = ?
	 * is called, thus deleting empty bank from user
	 * calls DB
	 * precondition: currentUser must be logged in
	 */
	@Override
	public void clearEmptyAccount() {
		for (int i = 0; i < currentUser.showAccount().size(); i++) {
			if (currentUser.showMoney().get(i) == 0.0) {
				try (Connection conn = ConnectionUtil.getConnection()) {
					String sql = "DELETE  FROM BANK_ACCOUNT WHERE BANK_ACCOUNT_ID = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, currentUser.showAccount().get(i));
					ResultSet rs = pstmt.executeQuery();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	/**
	 * Shows the transaction history of 
	 * ONE OF CURRENTUSER'S BANK_ACCOUNT
	 * requires a valid bank_id, logical/sanity check occurs here
	 * once found calls sql command
	 * SELECT * FROM TRANSACTION_HISTORY WHERE ACCOUNT_ID = ? ORDER BY TRANSACTION_ID ASC
	 * prints all entries, formated
	 */
	@Override
	public void showTransaction(int id) {
		int pos = -1;
		ArrayList<Double> log = new ArrayList<Double>();
		for (int i = 0; i < currentUser.showAccount().size(); i++) {
			if (currentUser.showAccount().get(i) == id)
				pos = i;
		}
		if (pos == -1) {
			System.out.println("No Account found");
		} else {
			try (Connection conn = ConnectionUtil.getConnection()) {
				String sql = "SELECT * FROM TRANSACTION_HISTORY WHERE ACCOUNT_ID = ? ORDER BY TRANSACTION_ID ASC";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					log.add(rs.getDouble("TRANSFER"));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Log Of transaction history\n------------------------");
			for (int i = 0; i < log.size(); i++) {
				if (log.get(i) > 0) {
					System.out.println("Entry #" + (i + 1) + " Deposit:\t" + log.get(i));
				} else
					System.out.println("Entry #" + (i + 1) + " Withdrawn:\t" + Math.abs(log.get(i)));
			}
		}
	}
	/**
	 * creates a new user
	 * NOTE: currentUser can be null or not null
	 * calls precedure
	 * SP_INSERT_USER(?,?)
	 * requests user input for 2 string
	 * logical/sanity check occurs here
	 * 
	 * APPENDENGE: sql command commit;
	 * 	Due to multiple performance issues this additional command will be 
	 * called to improve performance speed. Once issue is resolved will be removed
	 */
	@Override
	public void create(String un, String pw) {
		if (un == null || pw == null)
			System.out.println("Failed to create user, invalid input");
		else {
			try (Connection conn = ConnectionUtil.getConnection()) {
				String pro = "{call SP_INSERT_USER(?,?)}";
				CallableStatement po = conn.prepareCall(pro);
				po.setString(1, un);
				po.setString(2, pw);
				boolean result = po.execute();
				//remove start
				String sqlplus = "commit";
				Statement sm = conn.createStatement();
				ResultSet ss = sm.executeQuery(sqlplus);
				//remove end
				System.out.println("User created");

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * ADMIN command only
	 * redencent checks for admin priv
	 * 
	 * deletes a user called by user id
	 * calls the sql command
	 * DELETE  FROM BANK_USER WHERE USER_ID = ?
	 * CONDITIONS:  account must exist
	 * 				user must exist
	 * 				All account must have 0 balance
	 * 
	 * admin can't delete other admins or itself
	 * sanity check, no logical check 
	 */
	@Override
	public void deleteUser(int id) {
		double money = 0;
		for (int i = 0; i < us.size(); i++) {
			if (id == us.get(i).getUserId()) {
				for (double d : us.get(i).showMoney()) {
					money += d;
				}
			}
		}
		if (currentUser.getUserId() == id) {
			System.out.println("You can't delete yourself");
		} else if (money != 0.0) {
			System.out.println("This user has " + money + ". In their account. \nCan't delete");
		} else {
			boolean flag = false;
		//	System.out.println("here3");
			for (int i = 0; i < us.size(); i++) {
				if (us.get(i).getUserId() == id) {
				//	System.out.println("here2");
					try (Connection conn = ConnectionUtil.getConnection()) {
						String sql = "DELETE  FROM BANK_USER WHERE USER_ID = ?";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, id);
						ResultSet rs = pstmt.executeQuery();
						//System.out.println("here1");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					flag = true;
				}

			}
			if (!flag)
				System.out.println("No User Deleted");
			else
				System.out.println("User Deleted");
		}
	}

}
