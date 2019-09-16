package Week2Challenge.DAO;

import java.util.List;

import Week2Challenge.beans.*;

public interface EmployeeDao {

		public List<Employee> getEmployee();
		public Employee getEmployeeById( int id);
	//	public void insertEmployee ( Employee e);
		public void giveRaise (int id);
}
