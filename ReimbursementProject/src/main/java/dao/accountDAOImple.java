package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Reimburse;
import bean.User;
import util.ConnectionUtil;

public class accountDAOImple implements accountDAO {

	@Override
	public ArrayList<Reimburse> getReimburse(User u) {
		ArrayList<Reimburse> r = new ArrayList<Reimburse>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = "Select * FROM REIMBURSEMENT WHERE EMP_ID= " + u.getId();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			while (rs.next()) {
				int id = rs.getInt("RE_ID");
				double money = rs.getDouble("AMOUNT");
				int s = rs.getInt("STATUS");
				boolean status = false;
				if (s > 0)
					status = true;
				r.add(new Reimburse(id, money, status, u.getName()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public void makeReimburse(User u, double money) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = " insert into REIMBURSEMENT (EMP_ID, AMOUNT)" + " values (?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlplus);
			preparedStmt.setInt(1, u.getId());
			preparedStmt.setDouble(2, money);
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

	@Override
	public void acceptReimburse(User u, int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = "Select * FROM REIMBURSEMENT WHERE RE_ID = " + id;
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);

			User us;
			int EMP_ID = 0;
			while (rs.next()) {
				EMP_ID = rs.getInt("EMP_ID");
			}
			if (EMP_ID != 0) {
				sqlplus = "Select * FROM EMPLOYEE WHERE EMPLOYEE_ID = " + EMP_ID;
				Statement sm1 = conn.createStatement();
				ResultSet rs1 = sm1.executeQuery(sqlplus);
				int EMP_MAN = 0;
				while (rs1.next()) {
					EMP_MAN = rs1.getInt("EMP_MAN");
				}
				if (u.getId() == EMP_MAN) {
					try {
						sqlplus = " update REIMBURSEMENT SET STATUS=? WHERE RE_ID = ?";
						PreparedStatement preparedStmt = conn.prepareStatement(sqlplus);
						preparedStmt.setInt(1, 1);
						preparedStmt.setInt(2, id);
						System.out.println("here2");

						if (preparedStmt.executeUpdate() == 1)
							System.out.println("yes");

						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modifyUser(User u, String newpass) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = " update EMPLOYEE SET EMP_PASSWORD=? WHERE EMPLOYEE_ID = ?";
			PreparedStatement preparedStmt = conn.prepareStatement(sqlplus);
			preparedStmt.setString(1, newpass);
			preparedStmt.setInt(2, u.getId());
			preparedStmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Reimburse> getEmployeeRe(User u) {

		ArrayList<Reimburse> r = new ArrayList<Reimburse>();
		ArrayList<User> us = new ArrayList<User>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = "Select * FROM EMPLOYEE WHERE EMP_MAN = " + u.getId();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			while (rs.next()) {
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

			for (User emp : us) {
				sqlplus = "Select * FROM REIMBURSEMENT WHERE EMP_ID= " + emp.getId();
				Statement sm1 = conn.createStatement();
				ResultSet rs1 = sm1.executeQuery(sqlplus);
				while (rs1.next()) {
					int id = rs1.getInt("RE_ID");
					double money = rs1.getDouble("AMOUNT");
					int s = rs1.getInt("STATUS");
					boolean status = false;
					if (s != 0)
						status = true;
					r.add(new Reimburse(id, money, status, emp.getName()));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection");
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (u.getRank() >= 1)
			return r;
		else
			return null;
	}

	@Override
	public ArrayList<User> getAllUser() {
		ArrayList<User> us = new ArrayList<User>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = "Select * FROM EMPLOYEE";
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			while (rs.next()) {
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
		return us;
	}

	@Override
	public ArrayList<Reimburse> getCompleteRe() {
		ArrayList<Reimburse> r = new ArrayList<Reimburse>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = "SELECT * FROM reimbursement INNER JOIN employee "
					+ "ON reimbursement.EMP_ID=employee.EMPLOYEE_ID WHERE reimbursement.status=1";
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			while (rs.next()) {
				int id = rs.getInt("RE_ID");
				double money = rs.getDouble("AMOUNT");
				int s = rs.getInt("STATUS");

				boolean status = false;
				if (s > 0)
					status = true;
				String name = rs.getString("EMP_NAME");
				r.add(new Reimburse(id, money, status, name));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public ArrayList<User> getMyEmployee(int id) {
		ArrayList<User> us = new ArrayList<User>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sqlplus = "Select * FROM EMPLOYEE WHERE EMP_MAN = " +id;
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			while (rs.next()) {
				int rank = rs.getInt("EMP_RANK");
				int reid = rs.getInt("EMPLOYEE_ID");
				String name = rs.getString("EMP_NAME");
				String email = rs.getString("EMP_EMAIL");
				String password = rs.getString("EMP_PASSWORD");
				if ((rank == 0 && reid == 0) || (name == null) || (email == null) || (password == null))
					System.out.println("failed to build user");
				else
					us.add(new User(rank, reid, name, email, password));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return us;
	}

	@Override
	public String getNameWithId(int id) {
		String name = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sqlplus = "Select * FROM EMPLOYEE WHERE EMPLOYEE_ID = " +id;
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			while (rs.next()) {
	
				name	 = rs.getString("EMP_NAME");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}

}
