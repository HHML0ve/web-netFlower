package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import util.DBHelper;

public class MinxiDao {
	public void buyGoods(int accountid, Map<Integer, Integer> cartItem, double totalmoney) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql1 = null;
		try {
			conn = DBHelper.getConnection();
			conn.setAutoCommit(false);
			sql1 = "UPDATE account SET money = money - ? WHERE accountid = ?";
			stmt = conn.prepareStatement(sql1);
			stmt.setDouble(1, totalmoney);
			stmt.setInt(2, accountid);
			stmt.executeUpdate();
			Iterator<Entry<Integer, Integer>> iter = cartItem.entrySet().iterator();
			while (iter.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry entry = (Map.Entry) iter.next();
				String sql2 = "UPDATE items SET number = number - ? WHERE itemsid = ?";
				stmt = conn.prepareStatement(sql2);
				stmt.setInt(1, (Integer) entry.getKey());
				stmt.setInt(2, (Integer) entry.getValue());
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			throw new RuntimeException("明细插入失败");
		} finally {
			if (null != stmt) {
				stmt.close();
				stmt = null;
			}
		}
	}

	@SuppressWarnings("resource")
	public void buyGood(int accountid, int itemsid, int num, float price) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		float totalmoney = price * num;
		try {
			conn = DBHelper.getConnection();
			conn.setAutoCommit(false);

			String sql2 = "UPDATE items SET number = number - ? WHERE itemsid = ?";
			stmt = conn.prepareStatement(sql2);
			stmt.setFloat(1, num);
			stmt.setInt(2, itemsid);
			stmt.executeUpdate();

			String sql1 = "UPDATE account SET money = money - ? WHERE accountid = ?";
			stmt = conn.prepareStatement(sql1);
			stmt.setFloat(1, totalmoney);
			stmt.setInt(2, accountid);
			stmt.executeUpdate();

			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			if (null != stmt) {
				stmt.close();
				stmt = null;
			}
		}
	}

	public void insertMinXi(int accountid, int itemsid, int num, float price) {
		Connection conn = null;
		PreparedStatement stmt = null;
		float totalmoney = price * num;
		String sql = "INSERT INTO minxi VALUES(?,?,?,?,?)";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, accountid);
			stmt.setInt(2, itemsid);
			stmt.setInt(3, num);
			stmt.setFloat(4, totalmoney);
			stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}