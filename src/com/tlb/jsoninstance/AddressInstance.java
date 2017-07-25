package com.tlb.jsoninstance;

import com.tlb.entity.Address;

public class AddressInstance {
	private int status_no;
	private String username;
	private Address address;
	private int configType;

	public AddressInstance(int status_no) {
		super();
		this.status_no = status_no;
	}

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

	public int getStatus_no() {
		return status_no;
	}

	public void setStatus_no(int status_no) {
		this.status_no = status_no;
	}

}
