package db;

import dao.AccessFlowerImpl;
import dao.AccessUserImpl;
import dao.IFlower;
import dao.IUser;
import dao.MysqlFlowerImpl;
import dao.MysqlUserImpl;

public class DataAccess {
	private static String db = "Mysql";

	// private static String db = "Access";
	// private Static String db = "Oracle";
	public  IUser CreateUser() {
		IUser iUser = null;
		switch (db) {
		case "Mysql":
			iUser = new MysqlUserImpl();
			break;
		case "Access":
			iUser = new AccessUserImpl();
			break;
		default:
			break;
		}
		return iUser;
	}

	public IFlower CreateFlower() {
		IFlower iFlower = null;
		switch (db) {
		case "Mysql":
			iFlower = new MysqlFlowerImpl();
			break;
		case "Access":
			iFlower = new AccessFlowerImpl();
			break;
		default:
			break;
		}
		return iFlower;
	}

}
