package com.tlb.entity;

public class Category {
	private int categoryID;
	private String categoryName;

	public Category() {
		super();
	}

	public Category(int categoryID) {
		super();
		this.categoryID = categoryID;
	}

	public Category(int categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
