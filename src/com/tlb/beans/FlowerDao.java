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
				try {
					ResultSet rstCategory = null;
					PreparedStatement pstmtCategory = conn
							.prepareStatement("SELECT *FROM category WHERE categoryID=?");
					pstmtCategory.setInt(1, categoryID);
					rstCategory = pstmtCategory.executeQuery();
					if (rstCategory.next()) {
						System.out.print("i am here");
						String categoryName = rstCategory.getString("categoryName");
						category.setCategoryID(categoryID);
						category.setCategoryName(categoryName);
					}

				} catch (Exception e) {
					System.out.print(e);
				} 
				Flower flowerObj = new Flower(flowerID, flowerName, flowerDesc, flowerImg, flowerTotal, flowerPrice,
						category);
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
