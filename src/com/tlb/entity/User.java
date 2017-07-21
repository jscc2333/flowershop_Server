package com.tlb.entity;

import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private ArrayList<Order> orders;
	private ArrayList<Address> addresses;

	public User(String username, String password) {
		super();
		orders = new ArrayList<Order>(0);
		addresses = new ArrayList<Address>(0);
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
