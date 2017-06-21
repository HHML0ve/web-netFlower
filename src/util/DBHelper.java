package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
	static Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	private static final String driver = "com.mysql.jdbc.Driver"; 
	private static final String url = "jdbc:mysql://localhost:3305/goods?useUnicode=true&characterEncoding=UTF-8";
	private static final String username = "root";
	private static final String password = "admin";
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws Exception{
		if(conn==null){
			conn = DriverManager.getConnection(url,username,password);
			return conn;
		}
		return conn;
	}

}
