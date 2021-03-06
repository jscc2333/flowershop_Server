package com.tlb.entity;

public class Address {
	private int addressID;
	private String addressname;
	private String consignee;
	private String phonenumber;
	private Boolean defaultAddress; 
	public Address(int addressID, String addressname, String consignee, String phonenumber,Boolean defaultAddress) {
		super();
		this.addressID = addressID;
		this.addressname = addressname;
		this.consignee = consignee;
		this.phonenumber = phonenumber;
		this.defaultAddress = defaultAddress;
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

	public Boolean getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(Boolean defaultAddress) {
		this.defaultAddress = defaultAddress;
	}
}
