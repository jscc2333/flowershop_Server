package com.tlb.jsoninstance;

import java.util.ArrayList;

import com.tlb.entity.Category;
import com.tlb.entity.Flower;

public class HomeInstance {
	private int status_no;
	private ArrayList<Category> categories;
	private ArrayList<Flower> flowers;
	
	public HomeInstance(int status_no, ArrayList<Category> categories,ArrayList<Flower> flowers) {
		super();
		this.status_no = status_no;
		this.categories = categories;
		this.flowers = flowers;
	}
	public int getStatus_no() {
		return status_no;
	}
	public void setStatus_no(int status_no) {
		this.status_no = status_no;
	}
	public ArrayList<Category> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}
	public ArrayList<Flower> getFlowers() {
		return flowers;
	}
	public void setFlowers(ArrayList<Flower> flowers) {
		this.flowers = flowers;
	}
}
