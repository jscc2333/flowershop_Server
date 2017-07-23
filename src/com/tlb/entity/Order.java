package com.tlb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Order {
	private int orderID;
	private float orderTotal;
	private Date orderTime;
	private String orderAddress;

	private ArrayList<Flower> flowerList;

	public Order(int orderID, float orderTotal, Date orderTime,String orderAddress, ArrayList<Flower> flowerList) {
		super();
		this.orderID = orderID;
		this.orderTotal = orderTotal;
		this.orderTime = orderTime;
		this.orderAddress = orderAddress;
		this.flowerList = flowerList;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public float getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public ArrayList<Flower> getFlowerList() {
		return flowerList;
	}

	public void setFlowerList(ArrayList<Flower> flowerList) {
		this.flowerList = flowerList;
	}
}
