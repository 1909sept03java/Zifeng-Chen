package dao;

import bean.User;

import java.util.ArrayList;

import bean.Reimburse;
public interface accountDAO {
	public ArrayList<User> getAllUser();
	public ArrayList<User> getMyEmployee(int id);
	public ArrayList<Reimburse> getReimburse(User u);
	public ArrayList<Reimburse> getEmployeeRe(User u);
	public ArrayList<Reimburse> getCompleteRe();
	public void makeReimburse(User u, double money) ;
	public void acceptReimburse(User u,int id) ;
	public void modifyUser(User u,String newpass);
	public String getNameWithId(int id);
	
	
	
}
