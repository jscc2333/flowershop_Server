package com.tlb.beans;

import javax.naming.InitialContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sql.*;

import com.tlb.entity.Category;
import com.tlb.entity.Flower;
import com.tlb.global.Global;
import com.tlb.utils.DBUtils;

public class FlowerDao {
	private static InitialContext context = null;
	private DataSource dataSource = null;

	public ArrayList<Flower> getFlowers() {
		// 用ArrayList保存所有的花对象
		ArrayList<Flower> flowers = new ArrayList<Flower>(0);
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmtFlower = null;
		ResultSet rstFlowers = null;
		// ArrayList<Integer> availableList = new ArrayList<Integer>(0);
		// int availableLength = 0;
		// try {
		// PreparedStatement pstmtTemp = null;
		// ResultSet rstTemp = null;
		// pstmtTemp = conn.prepareStatement("SELECT flowerID FROM flower WHERE
		// flowerShow = 0");
		// rstTemp = pstmtTemp.executeQuery();
		// while (rstTemp.next()) {
		// availableLength += 1;
		// availableList.add(availableList.size(), rstTemp.getInt("flowerID"));
		// }
		// } catch (Exception e) {
		// System.out.print(e);
		// }
		//
		try {
			// if (availableLength == 0) {
			// return null;
			// }
			// String queryString = "SELECT *FROM flower WHERE flowerID in (";
			// String updateString = "UPDATE flower set flowerShow = 1 WHERE
			// flowerID in(";
			// int num = 0;
			// int numTemp = -1;
			// int realLength = 0;
			// if (availableLength < 5) {
			// realLength = availableLength;
			// } else {
			// realLength = 5;
			// }
			// ArrayList<Integer> tempList = new ArrayList<Integer>(0);
			// for (int i = 0; i < realLength; i++) {
			// do {
			// numTemp = (int) (Math.random() * 11+1);
			// if (availableList.contains(numTemp) &&
			// !tempList.contains(numTemp)) {
			// num = numTemp;
			// tempList.add(tempList.size(), num);
			// break;
			// }
			// } while (numTemp != num || tempList.contains(numTemp));
			// if (i != realLength - 1) {
			// queryString += num + ",";
			// updateString += num + ",";
			// } else {
			// queryString += num + ") AND flowerShow = 0";
			// updateString += num + ")";
			// }
			// }
			pstmtFlower = conn.prepareStatement("SELECT * FROM flower");
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
			// try {
			// PreparedStatement pstmtTemp = null;
			// pstmtTemp = conn.prepareStatement(updateString);
			// } catch (Exception e) {
			// System.out.println(e);
			// }
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

	public int insertFlowers(Flower flower) {
		int status = 0;
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			int flowerID = 0;
			try {
				PreparedStatement pstmtTemp = conn.prepareStatement("SELECT * FROM flower");
				ResultSet rst = pstmtTemp.executeQuery();
				if (rst.next()) {
					rst.last();
					flowerID = rst.getRow() + 1;
				} else {
					flowerID = 1;
				}
			} catch (Exception e) {
				System.out.println(e);
			}

			pstmt = conn.prepareStatement("INSERT INTO flower(flowerID,categoryID,flowerName,"
					+ "flowerDesc,flowerImg,flowerTotal,flowerPrice) VALUES (?,?,?,?,?,?,?)");
			pstmt.setInt(1, flowerID);
			pstmt.setInt(2, flower.getCategory().getCategoryID());
			pstmt.setString(3, flower.getFlowername());
			pstmt.setString(4, flower.getFlowerdesc());
			pstmt.setString(5, flower.getFlowerimg());
			pstmt.setInt(6, flower.getFlowertotal());
			pstmt.setFloat(7, flower.getFlowerprice());
			result = pstmt.executeUpdate();
			if (result > 0) {
				status = Global.STATUS_OK;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return status;
	}

	public int updateFlower(Flower flower) {
		int status_no = 0;
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement("UPDATE flower SET categoryID=?,flowerName=?,flowerDesc=?,flowerImg=?,"
					+ "flowerTotal=?,flowerPrice=? WHERE flowerID=?");
			pstmt.setInt(1, flower.getCategory().getCategoryID());
			pstmt.setString(2, flower.getFlowername());
			pstmt.setString(3, flower.getFlowerdesc());
			pstmt.setString(4, flower.getFlowerimg());
			pstmt.setInt(5, flower.getFlowertotal());
			pstmt.setFloat(6, flower.getFlowerprice());
			pstmt.setInt(7, flower.getFlowerID());
			result = pstmt.executeUpdate();
			if (result > 0) {
				status_no = 1;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return status_no;
	}

	public int deleteFlower(Flower flower) {
		int status_no = 0;
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement("DELETE FROM flower WHERE flowerID = ?");
			pstmt.setInt(1, flower.getFlowerID());
			result = pstmt.executeUpdate();
			if (result > 0) {
				status_no = Global.STATUS_OK;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return status_no;
	}
}
