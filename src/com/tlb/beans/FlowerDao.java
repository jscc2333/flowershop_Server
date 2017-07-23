package com.tlb.beans;

import javax.naming.InitialContext;
import java.sql.*;
import java.util.ArrayList;

import javax.sql.*;

import com.tlb.entity.Category;
import com.tlb.entity.Flower;

public class FlowerDao {
	private static InitialContext context = null;
	private DataSource dataSource = null;

	public ArrayList<Flower> getFlowers() {
		// 用ArrayList保存所有的花对象
		ArrayList<Flower> flowers = new ArrayList<Flower>(0);
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmtFlower = null;
		ResultSet rstFlowers = null;
		try {
			pstmtFlower = conn.prepareStatement("SELECT *FROM flower");
			rstFlowers = pstmtFlower.executeQuery();
			while (rstFlowers.next()) {
				int flowerID = rstFlowers.getInt("flowerID");
				int categoryID = rstFlowers.getInt("categoryID");
				int flowerTotal = rstFlowers.getInt("flowerTotal");
				float flowerPrice = rstFlowers.getFloat("flowerPrice");
				String flowerName = rstFlowers.getString("flowerName");
				String flowerDesc = rstFlowers.getString("flowerDesc");
				String flowerImg = rstFlowers.getString("flowerImg");
				Category category = new Category();
				// 获取当前花的鲜花类别，并封装到category对象中
				try {
					ResultSet rstCategory = null;
					PreparedStatement pstmtCategory = conn.prepareStatement("SELECT *FROM category WHERE categoryID=?");
					pstmtCategory.setInt(1, categoryID);
					rstCategory = pstmtCategory.executeQuery();
					if (rstCategory.next()) {
						String categoryName = rstCategory.getString("categoryName");
						category.setCategoryID(categoryID);
						category.setCategoryName(categoryName);
					}

				} catch (Exception e) {
					System.out.print(e);
				}
				// 实例化鲜花对象容纳数据
				Flower flowerObj = new Flower(flowerID, flowerName, flowerDesc, flowerImg, flowerTotal, flowerPrice,
						category);
				// 添加到 ArrayList中以供后期使用
				flowers.add(flowers.size(), flowerObj);
			}
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.print(e);
			}
		}
		return flowers;
	}
}
