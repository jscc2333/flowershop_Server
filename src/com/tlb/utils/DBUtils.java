package com.tlb.utils;

import java.sql.*;

public class DBUtils {
	// 建立数据库连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306/flowershop?autoReconnect=true&useSSL=false";
			conn = DriverManager.getConnection(dburl, "root", "302434");
		} catch (Exception e) {
			return null;
		}
		return conn;
	}

//	public static int getTableLength(String tablename) {
//		Connection conn = DBUtils.getConnection();
//		PreparedStatement pstmt = null;
//		ResultSet rst = null;
//		int length = 0;
//		try {
//			System.out.print("run to here");
//			pstmt = conn.prepareStatement("SELECT * FROM ?");
//			pstmt.setString(1, "orders");
//			rst = pstmt.executeQuery();
//			rst.last();
//			length = rst.getRow();
//			rst.beforeFirst();
//		} catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			try {
//				conn.close();
//			} catch (Exception e) {
//				System.out.print(e);
//			}
//		}
//		return length;
//	}
}
