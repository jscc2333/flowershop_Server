package com.tlb.jsoninstance;

import java.util.ArrayList;

import com.tlb.entity.Address;
import com.tlb.entity.Order;

public class OrderInstance {
	private int status_no;
	private String username;
	private ArrayList<Order> orderList;

	public OrderInstance(int status_no, String username, ArrayList<Order> orderList) {
		super();
		this.status_no = status_no;
		this.username = username;
		this.orderList = orderList;
	}

	public int getStatus_no() {
		return status_no;
	}

	public void setStatus_no(int status_no) {
		this.status_no = status_no;
	}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
