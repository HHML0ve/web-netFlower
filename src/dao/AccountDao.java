package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Account;
import entity.Items;
import util.DBHelper;

public class AccountDao {
	public boolean insertAccount(Account user) {
		boolean flag = false;
		Connection conn = null;
		Statement stmt = null;
		boolean isAccount = islogin(user.getName(), user.getPassword());
		if (!isAccount) {
			try {
				conn = DBHelper.getConnection();
				String sql = "INSERT INTO account VALUES(null,'" + user.getName() + "','" + user.getPassword() + "','"
						+ user.getSex() + "','" + user.getTel() + "','" + user.getAddress() + "',0)";
				stmt = conn.createStatement();
				int isregister = stmt.executeUpdate(sql);
				if (isregister != 0)
					flag = true;
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
						stmt = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		return flag;
	}

	public boolean islogin(String name, String password) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "SELECT password FROM account WHERE accountname=?";
			stat = conn.prepareStatement(sql);
			stat.setString(1, name);
			rs = stat.executeQuery();
			while (rs.next()) {
				if (password.equals(rs.getString("password"))) {
					flag = true;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
					stat = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}

		return flag;
	}

	public boolean addMoney(int id, double money) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "UPDATE account SET money = ? WHERE accountid = ?";
			stmt.setDouble(1, money);
			stmt.setInt(2, id);
			int addM = stmt.executeUpdate(sql);
			if (addM != 0)
				flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return flag;
	}

	public Account getAccountByNameandPwd(String name, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "SELECT * FROM account WHERE accountname=? and password=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt("accountid"));
				account.setName(rs.getString("accountname"));
				account.setPassword(rs.getString("password"));
				account.setSex(rs.getString("sex"));
				account.setTel(rs.getString("tel"));
				account.setAddress(rs.getString("address"));
				account.setMoney(rs.getDouble("money"));
				return account;
			} else {
				return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
	}

	public boolean changePwd(Account account) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getConnection();
			String sql = "SELECT tel FROM account WHERE accountname=?";
			String chsql = "UPDATE account SET `password` = ? WHERE accountname = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getName());
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (account.getTel().equals(rs.getString("tel"))) {
					stmt = conn.prepareStatement(chsql);
					stmt.setString(1, account.getPassword());
					stmt.setString(2, account.getName());
					stmt.executeUpdate();
					flag = true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return flag;
	}
}
