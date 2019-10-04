package bean;

public class Reimburse {
	private int id;
	private double money;
	private boolean status;
	private String owner;

	@Override
	public String toString() {
		return "Reimburse [id=" + id + ", money=" + money + ", status=" + status + ", onwer=" + owner + "]";
	}

	public Reimburse(int id, double money, boolean status,String onwer) {
		super();
		this.id = id;
		this.money = money;
		this.status = status;
		this.owner = onwer;
	}
	
	public String getOnwer() {
		return owner;
	}
	public void setOnwer(String onwer) {
		this.owner = onwer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Reimburse other = (Reimburse) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
