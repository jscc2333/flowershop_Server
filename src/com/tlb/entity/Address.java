package com.tlb.entity;

public class Address {
	private int addressID;
	private String addressname;
	private String consignee;
	private String phonenumber;

	public Address(int addressID, String address, String consignee, String phonenumber) {
		super();
		this.addressID = addressID;
		this.addressname = address;
		this.consignee = consignee;
		this.phonenumber = phonenumber;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getAddressname() {
		return addressname;
	}

	public void setAddressname(String address) {
		this.addressname = address;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}
