
package com.tlb.beans;

import javax.naming.InitialContext;
import javax.sql.*;

import java.util.Date;

import com.google.gson.Gson;
import com.tlb.entity.Address;
import com.tlb.entity.Flower;
import com.tlb.entity.Order;
import com.tlb.global.Global;
import com.tlb.utils.DBUtils;

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
			int rstLength;
			int rstCount = 1;
			if (rst.next()) {
				rst.last();// 移动游标到最后
				rstLength = rst.getRow();
				rst.beforeFirst();// 还原游标到rs开始
			} else {
				return null;
			}
			while (rst.next()) {
				int orderID = rst.getInt("orderID");
				if (orderID != orderIDLast) {
					if (order != null) {
						orders.add(order);
					}
					flowerList = new ArrayList<Flower>(0);
					float orderTotal = rst.getFloat("orderTotal");
					Date orderTime = rst.getTimestamp("orderTime");
					int addressID = rst.getInt("addressID");
					int flowerNum = rst.getInt("flowerNum");
					int flowerID = rst.getInt("flowerID");
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
							Boolean defaultAddress = rstTemp.getBoolean("defaultAddress");
							address = new Address(addressID, addressname, consignee, phonenumber, defaultAddress);
						}
					} catch (Exception e) {
						System.out.print(e);
					}

					Flower flower = new Flower(flowerID, flowerName, flowerPrice, flowerImg, flowerNum);
					flowerList.add(flower);
					order = new Order(orderID, username, address, orderTotal, orderTime, flowerList);
					if (!rst.next() || rstLength == 1) {
						orders.add(orders.size(), order);
						break;
					}
					rst.previous();
					orderIDLast = orderID;
					rstCount++;
				} else if (orderID == orderIDLast) {
					int flowerID = rst.getInt("flowerID");
					int flowerNum = rst.getInt("flowerNum");
					String flowerName = rst.getString("flowerName");
					String flowerImg = rst.getString("flowerImg");
					float flowerPrice = rst.getFloat("flowerPrice");
					Flower flower = new Flower(flowerID, flowerName, flowerPrice, flowerImg, flowerNum);
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

	public int placeOrder(Order order) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmtOrder = null;
		int status_no = 0;
		int result = 0;
		try {
			pstmtOrder = conn.prepareStatement("SELECT * FROM orders");
			ResultSet rst = pstmtOrder.executeQuery();
			int orderID = 0;
			if (rst.next()) {
				rst.last();
				orderID = rst.getInt("orderID") + 1;
				rst.beforeFirst();
			} else {
				orderID = 1;
			}
			pstmtOrder = conn.prepareStatement(
					"INSERT INTO orders(addressID,orderID,username,orderTime,orderTotal)" + " VALUES(?,?,?,?,?)");
			int addressID = order.getAddress().getAddressID();
			String username = order.getUsername();
			float orderTotal = order.getOrderTotal();

			pstmtOrder.setInt(1, addressID);
			pstmtOrder.setInt(2, orderID);
			pstmtOrder.setString(3, username);
			pstmtOrder.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pstmtOrder.setFloat(5, orderTotal);
			result += pstmtOrder.executeUpdate();
			ArrayList<Flower> flowerList = order.getFlowerList();
			Iterator flowerIt = flowerList.iterator();
			while (flowerIt.hasNext()) {
				Flower flower = (Flower) flowerIt.next();
				int flowerNum = flower.getFlowerNum();
				int flowerID = flower.getFlowerID();
				PreparedStatement pstmtFlower = conn
						.prepareStatement("UPDATE flower SET flowerTotal =" + " flowerTotal - ?" + " WHERE flowerID=?");
				pstmtFlower.setInt(1, flowerNum);
				pstmtFlower.setInt(2, flowerID);
				result += pstmtFlower.executeUpdate();

				PreparedStatement pstmtFlowerOrders = conn
						.prepareStatement("INSERT INTO flower_orders(flowerID,orderID," + "flowerNum) VALUES(?,?,?)");
				pstmtFlowerOrders.setInt(1, flowerID);
				pstmtFlowerOrders.setInt(2, orderID);
				pstmtFlowerOrders.setInt(3, flowerNum);
				result += pstmtFlowerOrders.executeUpdate();
			}
			if (result == flowerList.size() * 2 + 1) {
				status_no = Global.STATUS_OK;
			} else {
				status_no = Global.STATUS_ERR;
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
