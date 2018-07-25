package com.zhidisoft.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
/**
 * 
 * @author 赵恒
 *
 * 2017年12月13日
 */
public class DBUtil {
	/*
	 * 加载数据库驱动
	 */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建数据库的连接
	 * @return
	 */
	private static Connection getConn() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://192.168.10.205:3306/jeesite", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	/**
	 * 执行sql语句，返回影响的行数
	 * 参数只有一个sql语句
	 * @param sql
	 * @return
	 */
	public static int update(String sql) {
		return update(sql, null);
	}
	/**
	 * 执行sql语句，返回影响的行数
	 * 参数为sql语句和相对应的Object数组
	 * @param sql
	 * @return
	 */
	public static int update(String sql, Object[] obj) {
		// 获取数据库连接
		Connection conn = getConn();
		// 创建执行对象
		PreparedStatement ps = null;
		int rows = 0;
		try {
			ps = conn.prepareStatement(sql);
			if (obj != null && obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i + 1, obj[i]);
				}
			}
			// 执行SQL语句
			rows = ps.executeUpdate();
			close(null, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	/**
	 * 查询方法，返回List<HashMap>对象，HashMap中存储表的每一行记录
	 * 参数只有一个sql语句
	 * @param sql
	 * @return
	 */
	public static List<Map<String, String>> query(String sql) {
		return query(sql, null);
		
	}

	/**
	 * 查询方法，返回List<HashMap>对象，HashMap中存储表的每一行记录
	 * 参数为sql语句和相对应的Object数组
	 * @param sql
	 * @return
	 */
	public static List<Map<String, String>> query(String sql, Object[] obj) {
		// 获取数据库连接
		Connection conn = getConn();
		// 创建执行对象
		PreparedStatement ps = null;
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		try {
			ps = conn.prepareStatement(sql);
			if (obj != null && obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i + 1, obj[i]);
				}
			}
			// 执行SQL语句，返回值为ResultSet对象
			ResultSet rs = ps.executeQuery();
			// 获取表的结构
			ResultSetMetaData metaData = rs.getMetaData();
			// 获得表的字段个数
			int count = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < count; i++) {
					//这里用getColumnLabel而不是getColumnName，是为了对字段名取别名
					map.put(metaData.getColumnLabel(i + 1), rs.getString(metaData.getColumnLabel(i + 1)));
				}
				arrayList.add(map);
			}
			
			close(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	/**
	 * 释放资源，按照后创建，先关闭的原则
	 * 
	 * @param rs
	 * @param ps
	 * @param conn
	 */
	private static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
