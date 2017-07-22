package com.tlb.beans;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.*;

import com.tlb.entity.Category;

public class CategoryDao {
	private static InitialContext context = null;
	private DataSource dataSource = null;

	public ArrayList<Category> getCategory() {
		ArrayList<Category> categories = new ArrayList<Category>(0);
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = conn.prepareStatement("SELECT *FROM category");
			rst = pstmt.executeQuery();
			while (rst.next()) {
				int categoryID = rst.getInt("categoryID");
				String categoryName = rst.getString("categoryName");
				Category category = new Category(categoryID, categoryName);
				categories.add(categories.size(), category);
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return categories;
	}
}
