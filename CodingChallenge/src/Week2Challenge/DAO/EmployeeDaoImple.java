package Week2Challenge.DAO;

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

import Week2Challenge.beans.Employee;
import Week2Challenge.util.ConnectionUtil;

public class EmployeeDaoImple implements EmployeeDao{

	@Override
	public List<Employee> getEmployee() {
		List<Employee> emp = new ArrayList<Employee>();
		try (Connection conn = ConnectionUtil.getConnection()){
			String sqlplus = "Select * FROM Employee";
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sqlplus);
			while(rs.next()) {
				emp.add(new Employee(rs.getString("EMP_FIRSTNAME"),
					rs.getString("EMP_LASTNAME") , rs.getString("EMP_EMAIL"),rs.getInt("DEPARTMENT_ID"),
					rs.getDouble("SALARY"),rs.getInt("EMPLOYEE_ID")
					));
			}
	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}

		
		return emp;
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee emp = null ;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
			emp =	new Employee(rs.getString("EMP_FIRSTNAME"),
						rs.getString("EMP_LASTNAME") , rs.getString("EMP_EMAIL"),rs.getInt("DEPARTMENT_ID"),
						rs.getDouble("SALARY"),rs.getInt("EMPLOYEE_ID")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return emp;
	}

	@Override
	public void giveRaise(int id) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String pro= "{call SP_GIVE_RAISE(?, ?)}";
			CallableStatement po = conn.prepareCall(pro);
			po.setInt(1, id);
			po.registerOutParameter(2, Types.DOUBLE);
			
			boolean result = po.execute();
			
			if(po.getDouble(2) == 0.0)
				System.out.println("Failed");
			else
				System.out.println("All employees in department id: " + id + "\ngot a 10% raise.\nAverage Salary of the Department: " + po.getDouble(2));

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
	public void giveRaise(int id,double value) {
		try (Connection conn = ConnectionUtil.getConnection()){
			String pro= "{call SP_GIVE_RAISE_VALUE(?, ?, ?)}";
			CallableStatement po = conn.prepareCall(pro);
			po.setInt(1, id);
			po.setDouble(2, value);
			po.registerOutParameter(3, Types.DOUBLE);
			
			boolean result = po.execute();
			if(po.getDouble(3)==0.0)
				System.out.println("Failed");
			else {
				System.out.println("All employees in department id: " + id + "\ngot a " + ((value-1)*100) + "% raise.\nAverage Salary of the Department: " +po.getDouble(3));
			}
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
