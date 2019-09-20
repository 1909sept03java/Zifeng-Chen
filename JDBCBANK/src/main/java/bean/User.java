package bean;

import java.util.ArrayList;

public class User {
	private ArrayList<Integer>account;
	private ArrayList<Double>money;
	private int userId;
	private boolean admin;
	private String userName;
	private String passCode;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int userId, int perm,String userName, String passcode) {
		super();
		this.userId = userId;
		this.passCode = passcode;
		this.userName = userName;
		account = new ArrayList<Integer>();
		money = new ArrayList<Double>();
		if(perm == 0)
			admin = false;
		else if(perm > 0)
			admin = true;
	}
	


	@Override
	public String toString() {
		return "User [account=" + account + ", money=" + money + ", userId=" + userId + ", admin=" + admin
				+ ", userName=" + userName + ", passCode=" + passCode + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId != other.userId)
			return false;
		return true;
	}
	public ArrayList<Double> showMoney()
	{
		return money;
	}
	public ArrayList<Integer> showAccount()
	{
		return account;
	}
	public double getMoney( int pos) {
		try {
		return money.get(pos);
		}
		catch  (IndexOutOfBoundsException e)
		{
			System.out.println("No Account ID here" +"\nYou should never see this line of code");
			return 0;
		}
	}
	public void changeMoney( int pos, double amount) {
		try {
				double temp = money.get(pos) + amount;
				money.set(pos, temp);
			}
			catch  (IndexOutOfBoundsException e)
			{
				System.out.println("No Account ID here" +"\nYou should never see this line of code");
			}
	}
	public void setMoney(double acc) {
		this.money.add(acc);
	}
	public int getAccount( int pos) {
		try {
		return account.get(pos);
		}
		catch  (IndexOutOfBoundsException e)
		{
			System.out.println("No Account ID here" +"\nYou should never see this line of code");
			return 0;
		}
	}
	public void setAccount(int acc) {
		this.account.add(acc);
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getPasscode() {
		return passCode;
	}
	public void setPasscode(String passcode) {
		this.passCode = passcode;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String username) {
		userName = username;
	}
	
}