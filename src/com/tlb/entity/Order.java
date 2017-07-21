package com.tlb.entity;

import java.util.Date;
import java.util.HashMap;

public class Order {
	private int orderID;
	private float orderTotal;
	private Date orderTime;
	private HashMap<Flower, Integer> flowerMap;

	public Order(int orderID, float orderTotal, Date orderTime, HashMap<Flower, Integer> flowerMap) {
		super();
		this.orderID = orderID;
		this.orderTotal = orderTotal;
		this.orderTime = orderTime;
		this.flowerMap = flowerMap;
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

	public HashMap<Flower, Integer> getFlowerMap() {
		return flowerMap;
	}

	public void setFlowerMap(HashMap<Flower, Integer> flowerMap) {
		this.flowerMap = flowerMap;
	}
}
