package Question7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EmployeeDriver {

	public static void main(String[] args) {
		ArrayList<Employee> em = new ArrayList<Employee>();
		em.add(new Employee("AAName","ZZdepartment",19));
		em.add(new Employee("BBName","YYdepartment",49));
		em.add(new Employee("CCName","XXdepartment",59));
		em.add(new Employee("DDName","WWdepartment",29));
		em.add(new Employee("EEName","VVdepartment",39));
		System.out.println("Unsorted list:");
		for(Employee e :em)
			System.out.println(e.toString());
		
		Collections.sort(em, new sortByAge());
		System.out.println("\nSorted by Age: ");
		for(Employee e : em)
			System.out.println(e.toString());
		
		Collections.sort(em, new sortByDepartment());
		System.out.println("\nSorted by Department: ");
		for(Employee e : em)
			System.out.println(e.toString());
		
		Collections.sort(em, new sortByName());
		System.out.println("\nSorted by Name:");
		for(Employee e: em)
			System.out.println(e.toString());
	
	}

}
class sortByName implements Comparator<Employee>
{
	@Override
	public int compare(Employee a, Employee b)
	{
		return (a.getName().compareTo(b.getName()));
	}
}
class sortByDepartment implements Comparator<Employee>
{
	@Override
	public int compare(Employee a, Employee b)
	{
		return (a.getDepartment().compareTo(b.getDepartment()));
	}
}
class sortByAge implements Comparator<Employee>
{
	@Override
	public int compare(Employee a, Employee b)
	{
		return a.getAge() - b.getAge();
	}
}