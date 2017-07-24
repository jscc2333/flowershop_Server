package com.tlb.entity;

public class Flower {
	private Integer flowerID;
	private String flowerName;
	private String flowerDesc;
	private String flowerImg;
	private Integer flowerTotal;
	private Integer flowerNum;
	private float flowerPrice;
	private Category category;

	public Flower(int flowerID, int flowerNum) {
		super();
		this.flowerID = flowerID;
		this.setFlowerNum(flowerNum);
	}

	public Flower(String flowerName, float flowerPrice, String flowerImg, int flowerNum) {
		super();
		this.flowerName = flowerName;
		this.flowerImg = flowerImg;
		this.flowerPrice = flowerPrice;
		this.setFlowerNum(flowerNum);
	}

	public Flower(Integer flowerID, String flowername, String flowerdesc, String flowerimg, Integer flowertotal,
			float flowerprice, Category category) {
		super();
		this.flowerID = flowerID;
		this.flowerName = flowername;
		this.flowerDesc = flowerdesc;
		this.flowerImg = flowerimg;
		this.flowerTotal = flowertotal;
		this.flowerPrice = flowerprice;
		this.category = category;
	}

	public Integer getFlowerID() {
		return flowerID;
	}

	public void setFlowerID(Integer flowerID) {
		this.flowerID = flowerID;
	}

	public String getFlowername() {
		return flowerName;
	}

	public void setFlowername(String flowername) {
		this.flowerName = flowername;
	}

	public String getFlowerdesc() {
		return flowerDesc;
	}

	public void setFlowerdesc(String flowerdesc) {
		this.flowerDesc = flowerdesc;
	}

	public String getFlowerimg() {
		return flowerImg;
	}

	public void setFlowerimg(String flowerimg) {
		this.flowerImg = flowerimg;
	}

	public Integer getFlowertotal() {
		return flowerTotal;
	}

	public void setFlowertotal(Integer flowertotal) {
		this.flowerTotal = flowertotal;
	}

	public float getFlowerprice() {
		return flowerPrice;
	}

	public void setFlowerprice(float flowerprice) {
		this.flowerPrice = flowerprice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getFlowerNum() {
		return flowerNum;
	}

	public void setFlowerNum(Integer flowerNum) {
		this.flowerNum = flowerNum;
	}
}
