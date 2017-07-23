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
		//用ArrayList保存所有鲜花类别对象
		ArrayList<Category> categories = new ArrayList<Category>(0);
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
//			查询所有的鲜花类别
			pstmt = conn.prepareStatement("SELECT *FROM category");
			rst = pstmt.executeQuery();
			while (rst.next()) {
				int categoryID = rst.getInt("categoryID");
				String categoryName = rst.getString("categoryName");
				//实例化一个新的对象，来容纳数据
				Category category = new Category(categoryID, categoryName);
				//将数据填充到ArrayList中
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
