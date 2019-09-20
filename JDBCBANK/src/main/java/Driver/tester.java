package Driver;
import dao.UserControlDaoimplement;
import util.ConnectionUtil;

import org.junit.Assert;
import bean.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.*;
public class tester{
	
	public static void main(String[] args) {


		TestRead();
		TestOutputtext();
		TestUser();
		TestAdmin();
		TestTrans();
		TestIO();
		//System.out.println("No output logs, is good output logs");
	}
	@Test
	public static void TestTrans()
	{
		ArrayList<String>us = new ArrayList<String>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = "Select * FROM BANK_ACCOUNT";
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			while (rs.next()) {
				us.add(rs.getString("BANK_ACCOUNT_ID"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(us);
	}
	@Test
	public static void TestIO() {
		
	}
	@Test
	public static void TestRead()
	{
		ArrayList<User>us = new ArrayList<User>();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(us);
	}
	@Test
	public static void TestOutputtext( ) {
		String str = String.format("%-2d   word %.2f   word", 1,123.02323);
		assertEquals("1    word 123.02   word",str);
	}
	@Test
	public static void TestUser() {
		UserControlDaoimplement e = new UserControlDaoimplement();
		
		e.getUser();
		assertEquals(e.getCurrentUser(), "Not Logged in");
		if(e.login("SYS", "USER"))
		assertEquals(e.getCurrentUser(),"SYS");
		
	}
	@Test
	public static void TestAdmin() {
		ArrayList<User>us = new ArrayList<User>();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (User s : us) {
			if(s.getUsername()=="SYS") {
				assertTrue(s.isAdmin());
			}
			//else
			//	assertFalse(s.isAdmin());
		}
		
	}
}