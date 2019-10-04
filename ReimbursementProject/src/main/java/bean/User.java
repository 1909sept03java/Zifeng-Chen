package bean;

public class User {
	
	private int rank;
	private int id;
	private String name;
	private String email;
	private String password;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int rank, int id, String name, String email, String password) {
		super();
		this.rank = rank;
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [rank=" + rank + ", id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ "]";
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}