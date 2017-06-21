package dao;

import entity.Account;

public interface IUser {
	public boolean insertAccount(Account user);
	public boolean islogin(String name, String password);
	public boolean addMoney(int id, double money);
	public Account getAccountByNameandPwd(String name, String password);
	public boolean changePwd(Account account);
}
