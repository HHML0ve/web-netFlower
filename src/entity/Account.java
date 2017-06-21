package entity;

/*
 * �û�
 * */
public class Account {
	private int id;
	private String name;
	private String password;
	private String sex;
	private String tel;
	private Double money;
	private String address;

	public Account() {
		super();
	}

	
	public Account(String name, String tel) {
		super();
		this.name = name;
		this.tel = tel;
	}


	public Account(String name, String password, String sex, String tel, Double money, String address) {
		super();
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.tel = tel;
		this.money = money;
		this.address = address;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex + ", tel=" + tel
				+ ", money=" + money + ", address=" + address + "]";
	}

}
