package com.tlb.beans;

import javax.naming.InitialContext;
import javax.sql.*;

import com.tlb.entity.Address;
import com.tlb.entity.Flower;
import com.tlb.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class OrderDao {
	private static InitialContext context = null;
	private DataSource dataSource = null;

	public ArrayList<Order> getOrders(String username) {
		ArrayList<Order> orders = new ArrayList<Order>(0);
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			pstmt = conn.prepareStatement("SELECT flower.*,orders.*,flower_orders.flowerNum FROM"
					+ " flower,orders,flower_orders WHERE flower.flowerID = flower_orders.flowerID AND "
					+ "orders.orderID = flower_orders.orderID AND orders.username = ?");
			pstmt.setString(1, username);
			rst = pstmt.executeQuery();
			int orderIDLast = -1;
			ArrayList<Flower> flowerList = new ArrayList<Flower>(0);
			Order order = null;
			rst.last();// 移动游标到最后
			int rstLength = rst.getRow();
			int rstCount = 1;
			rst.beforeFirst();
			; // 还原游标到rs开始
			while (rst.next()) {
				int orderID = rst.getInt("orderID");
				if (orderID != orderIDLast) {
					if (order != null) {
						orders.add(orders.size(), order);
					}
					flowerList.clear();
					float orderTotal = rst.getFloat("orderTotal");
					Date orderTime = rst.getDate("orderTime");
					int addressID = rst.getInt("addressID");
					int flowerNum = rst.getInt("flowerNum");
					String flowerName = rst.getString("flowerName");
					String flowerImg = rst.getString("flowerImg");
					float flowerPrice = rst.getFloat("flowerPrice");
					Address address = null;
					try {
						PreparedStatement pstmtTemp = conn.prepareStatement("SELECT *FROM address WHERE addressID=?");
						pstmtTemp.setInt(1, addressID);
						ResultSet rstTemp = pstmtTemp.executeQuery();
						if (rstTemp.next()) {
							String consignee = rstTemp.getString("consignee");
							String addressname = rstTemp.getString("addressname");
							String phonenumber = rstTemp.getString("phonenumber");
							address = new Address(addressID, addressname, consignee, phonenumber);
						}
					} catch (Exception e) {
						System.out.print(e);
					}

					Flower flower = new Flower(flowerName, flowerPrice, flowerImg, flowerNum);
					flowerList.add(flowerList.size(), flower);
					order = new Order(username, orderID, addressID,orderTotal, orderTime, flowerList);
					if (rstLength == 1) {
						System.out.println("run to here yo");
						orders.add(orders.size(), order);
					}
					orderIDLast = orderID;
					rstCount++;
				} else if (orderID == orderIDLast) {
					int flowerNum = rst.getInt("flowerNum");
					String flowerName = rst.getString("flowerName");
					String flowerImg = rst.getString("flowerImg");
					float flowerPrice = rst.getFloat("flowerPrice");
					Flower flower = new Flower(flowerName, flowerPrice, flowerImg, flowerNum);
					flowerList.add(flower);
					order.setFlowerList(flowerList);
					if (rstCount == rstLength) {
						orders.add(orders.size(), order);
					}
					rstCount++;
				}
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
		return orders;
	}

	public int placeOrders(String username, Order order) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;
		try {

		} catch (Exception e) {

		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}
		int status_no = 0;

		return status_no;
	}
}
