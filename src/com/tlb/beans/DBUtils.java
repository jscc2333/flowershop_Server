package com.tlb.beans;

import java.sql.*;

public class DBUtils {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306/flowershop";
			conn = DriverManager.getConnection(dburl, "root", "302434");
		} catch (Exception e) {
			return null;
		}
		return conn;
	}
}
