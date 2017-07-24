package com.tlb.jsoninstance;

import com.tlb.entity.Address;

public class AddressInstance {
	private String username;
	private Address address;
	private int configType;
	public AddressInstance(String username, Address address, int configType) {
		super();
		this.username = username;
		this.address = address;
		this.configType = configType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Address getAddress() {
		return address;
	}
	public void setOrder(Address address) {
		this.address = address;
	}
	public int getConfigType() {
		return configType;
	}
	public void setConfigType(int configType) {
		this.configType = configType;
	}
	
}
