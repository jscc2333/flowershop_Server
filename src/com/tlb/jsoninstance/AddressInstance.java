package com.tlb.jsoninstance;

import java.util.ArrayList;

import com.tlb.entity.Address;

public class AddressInstance {
	private int status_no;
	private String username;
	private Address address;
	private ArrayList<Address> addressList;

	public AddressInstance(int status_no, String username, Address address, ArrayList<Address> addressList) {
		super();
		this.username = username;
		this.address = address;
		this.status_no = status_no;
		this.addressList = addressList;
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
		return status_no;
	}

	public void setConfigType(int configType) {
		this.status_no = configType;
	}

	public int getStatus_no() {
		return status_no;
	}

	public void setStatus_no(int status_no) {
		this.status_no = status_no;
	}

	public ArrayList<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(ArrayList<Address> addressList) {
		this.addressList = addressList;
	}

}
