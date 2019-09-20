package dao;

import java.util.List;

import bean.User;

public interface UserControlDao {

		public void getUser();
		public boolean login(String un, String pw);
		public void create(String un, String pw);
		public void deleteUser(int id);
		public void logout();
		public void showAllUsers();
		public void showAccount();
		public void createNewAccount();
		public void clearEmptyAccount();
	//	public void changeBalance();
	//	public void giveRaise (int id);
		public void changeBalance(int id, double amount);
		public void showTransaction(int id);
}
