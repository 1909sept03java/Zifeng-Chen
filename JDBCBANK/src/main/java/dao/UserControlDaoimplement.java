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

import util.ConnectionUtil;
import bean.User;

public class UserControlDaoimplement implements UserControlDao{
	private ArrayList<User> us = new ArrayList<User>();
	private User currentUser = null;
	public String getCurrentUser()
	{
		if( currentUser!= null ) {
			return currentUser.getUsername();
		}
		else
			return "Not Logged in";
	}
	public boolean getPriv()
	{
		return currentUser.isAdmin();
	}
	@Override
	public void getUser() {
		us = null;
		us = new ArrayList<User>();
		currentUser = null;
		try (Connection conn = ConnectionUtil.getConnection()){
			String sqlplus = "Select * FROM BANK_USER";
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			while(rs.next()) {
				us.add(new User(
						rs.getInt("USER_ID"),
						rs.getInt("PERM"),
						rs.getString("USER_NAME"),
						rs.getString("PASSCODE")
						));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		for(User u : us)
		{
			try ( Connection conn = ConnectionUtil.getConnection()) {
				String sql = "SELECT * FROM BANK_ACCOUNT WHERE USER_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, u.getUserId());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					u.setAccount(rs.getInt("BANK_ACCOUNT_ID"));
					u.setMoney(rs.getDouble("MONEY")); //name unknow
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		
		
		
		
	}
	
	@Override
	public boolean  login(String un, String pw) {
		for(User u : us)
		{
			if(u.getUsername().equals(un) && u.getPasscode().equals(pw))
			{
				currentUser = u;
				return true;
				
			}
		}
		return false;
	}
	@Override
	public void logout() {
		currentUser = null;
	}
	
	@Override
	public void showAllUsers() {
	if(currentUser.isAdmin()) {
		for(User s: us)
		System.out.println(s.toString());
	}
	else
		System.out.println("Not the Admin user, can't show all");
	}
	@Override
	public void showAccount() {
		System.out.println("Your Accounts are: \nAccount ID   Balance");
		if(!currentUser.showAccount().isEmpty()) {
		
			for( int  i = 0; i < currentUser.showAccount().size();i++ )
			{
				System.out.println(String.format("%-2d           %.2f",currentUser.showAccount().get(i), currentUser.showMoney().get(i)));
			}
		
		}
		else
			System.out.println("It is empty");
	}
	@Override
	public void changeBalance(int id, double amount) {
		int pos = -1;
		for(int i = 0 ; i < currentUser.showAccount().size();i++)
		{
			if(currentUser.showAccount().get(i) == id)
				pos = i;
		}
		if(pos == -1) {
			System.out.println("No Account found");
		}
		else {
			if( (amount * -1) > currentUser.showMoney().get(pos))
			{
				System.out.println("Withdraw too much money");
			}
			else {
				
				try (Connection conn = ConnectionUtil.getConnection()){
					String pro= "{call SP_TRANSACTION(?, ?)}";
					CallableStatement po = conn.prepareCall(pro);
					po.setInt(1, id);
					po.setDouble(2, amount);
					boolean result = po.execute();
					currentUser.changeMoney(pos, amount);
					System.out.println("done");
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}

			}
			
			
		}
	}
	@Override
	public void createNewAccount() {
		System.out.println( currentUser.getUserId());
		try (Connection conn = ConnectionUtil.getConnection()){
			String pro= "{call SP_INSERT_ACCOUNT(?)}";
			CallableStatement po = conn.prepareCall(pro);
			po.setInt(1, currentUser.getUserId());	
			boolean result = po.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	@Override
	public void clearEmptyAccount() {
		for( int i = 0; i < currentUser.showAccount().size();i++) {
			if( currentUser.showMoney().get(i) == 0.0)
			{
				try ( Connection conn = ConnectionUtil.getConnection()) {
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
	@Override
	public void showTransaction(int id) {
		int pos = -1;
		ArrayList<Double>log = new ArrayList<Double>();
		for(int i = 0 ; i < currentUser.showAccount().size();i++)
		{
			if(currentUser.showAccount().get(i) == id)
				pos = i;
		}
		if(pos == -1) {
			System.out.println("No Account found");
		}
		else {
			try ( Connection conn = ConnectionUtil.getConnection()) {
				String sql = "SELECT * FROM TRANSACTION_HISTORY WHERE ACCOUNT_ID = ? ORDER BY TRANSACTION_ID ASC";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,id);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
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
			for(int i = 0; i < log.size();i++) {
				if(log.get(i) > 0) {
					System.out.println("Entry #" + (i +1 ) +" Deposit:\t" + log.get(i));
				}
				else
					System.out.println("Entry #" +(i+1)+" Withdrawn:\t" + Math.abs( log.get(i)));
			}
		}
	}
	@Override
	public void create(String un, String pw) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String pro= "{call SP_INSERT_USER(?,?)}";
			CallableStatement po = conn.prepareCall(pro);
			po.setString(1, un);	
			po.setString(2, pw);
			boolean result = po.execute();
			
			String sqlplus = "commit";
			Statement sm = conn.createStatement();
			ResultSet ss = sm.executeQuery(sqlplus);
			
			
			System.out.println("User created");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	@Override
	public void deleteUser(int id) {
	double money = 0;
	for( int i = 0; i < us.size();i++) {
		if(id==us.get(i).getUserId()) {
			for(double d : us.get(i).showMoney()) {
				money += d;
			}
		}
	}
	if(currentUser.getUserId()==id) {
		System.out.println("You can't delete yourself");
	}
	else if (money != 0.0){
		System.out.println("This user has " + money + ". In their account. \nCan't delete");
	}
	else {
		boolean flag = false;
		for(int i = 0; i < us.size();i++) {
			if(us.get(i).getUserId()==id)
			{
				try ( Connection conn = ConnectionUtil.getConnection()) {
					String sql = "DELETE  FROM BANK_USER WHERE USER_ID = ?";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, id);
					ResultSet rs = pstmt.executeQuery();

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
		if(!flag) System.out.println("No User Deleted");
		else System.out.println("User Deleted");
	}
	}
	
}
