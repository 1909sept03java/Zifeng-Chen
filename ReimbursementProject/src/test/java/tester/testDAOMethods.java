package tester;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Reimburse;

import org.junit.Assert;
import org.junit.Test;

import bean.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import util.ConnectionUtil;

public class testDAOMethods {

	public static void main(String[] args) {
		testchangere();
		//changePassword();
		//addimburse();
		testConnect();
		testreimburse();
		testEmpRe();
	}
	
	@Test
	public static void testchangere() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = "Select * FROM REIMBURSEMENT WHERE RE_ID = "+12;	
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);

			User us;
			int EMP_ID = 0;
			while (rs.next()) {
				EMP_ID = rs.getInt("EMP_ID");
			}
			if(EMP_ID!= 0) {
				sqlplus = "Select * FROM EMPLOYEE WHERE EMPLOYEE_ID = "+EMP_ID;
				Statement sm1 = conn.createStatement();
				ResultSet rs1 = sm1.executeQuery(sqlplus);	
				int EMP_MAN=0;
				while (rs1.next()) {
					EMP_MAN = rs1.getInt("EMP_MAN");
				}
				
				assertEquals(EMP_MAN,101);
			}
			
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public static void testEmpRe() {
		ArrayList<Reimburse> r = new ArrayList<Reimburse>();
		ArrayList<Integer> usid = new ArrayList<Integer>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = "Select * FROM EMPLOYEE WHERE EMP_MAN=101 " ;
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			while (rs.next()) {
				int empid = rs.getInt("EMPLOYEE_ID");
				usid.add(empid);
			}
			for(int i : usid) {
				 sqlplus = "Select * FROM REIMBURSEMENT WHERE EMP_ID= " + i;
				 rs = sm.executeQuery(sqlplus);
				while (rs.next()) {
					int id = rs.getInt("RE_ID");
					double money = rs.getDouble("AMOUNT");
					int s = rs.getInt("STATUS");
					boolean status=false;
					if (s>0)
						status =true;
					r.add(new Reimburse(id, money, status,"un"));
				}
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(r.get(0).getId(),12);
	}
	public static void changePassword() {
		int user = 102;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = " update EMPLOYEE SET EMP_PASSWORD=? WHERE EMPLOYEE_ID = ?";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlplus);
			preparedStmt.setString(1, "test");
			preparedStmt.setInt(2, user);

			preparedStmt.execute();

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addimburse() {
		int user = 102;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = " insert into REIMBURSEMENT (EMP_ID, AMOUNT)" + " values (?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlplus);
			preparedStmt.setInt(1, user);
			preparedStmt.setDouble(2, 100);
			preparedStmt.execute();

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
@Test
	public static void testreimburse() {
		int user = 102;
		ArrayList<Reimburse> r = new ArrayList<Reimburse>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = "Select * FROM REIMBURSEMENT WHERE EMP_ID=" + user;
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			// System.out.println(rs.toString());
			while (rs.next()) {
			//	System.out.println("here2");
				int id = rs.getInt("RE_ID");
				double money = rs.getDouble("AMOUNT");
				int s = rs.getInt("STATUS");
				boolean status = false;
				if (s > 0)
					status = true;
				r.add(new Reimburse(id, money, status,"teser name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(r);
		assertEquals(r.get(0).getId(),12);
	}
	@Test
	public static void testConnect() {
		ArrayList<User> us = new ArrayList<User>();
		User u = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = "Select * FROM EMPLOYEE";
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			//System.out.println(rs.toString());
			while (rs.next()) {
				//System.out.println("here");
				int rank = rs.getInt("EMP_RANK");
				int id = rs.getInt("EMPLOYEE_ID");
				String name = rs.getString("EMP_NAME");
				String email = rs.getString("EMP_EMAIL");
				String password = rs.getString("EMP_PASSWORD");
				if ((rank == 0 && id == 0) || (name == null) || (email == null) || (password == null))
					System.out.println("failed to build user");
				else
					us.add(new User(rank, id, name, email, password));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(us.get(0).getName(),"HR");
		//System.out.println(us.toString());

	}
}
