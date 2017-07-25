package com.tlb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Order {
	private String username;
	private int orderID;
	private int addressID;
	private float orderTotal;
	private Date orderTime;
	private ArrayList<Flower> flowerList;
	
	public Order(String username, int orderID, int addressID, float orderTotal, Date orderTime,
			ArrayList<Flower> flowerList) {
		super();
		this.orderID = orderID;
		this.orderTotal = orderTotal;
		this.orderTime = orderTime;
		this.addressID = addressID;
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

	public ArrayList<Flower> getFlowerList() {
		return flowerList;
	}

	public void setFlowerList(ArrayList<Flower> flowerList) {
		this.flowerList = flowerList;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
