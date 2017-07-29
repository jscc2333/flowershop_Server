/*
 * 封装登录以后进入首页获取的数据，用类的形式封装起来，以便于Gson调用将数据转换为json格式，方便前端使用
 */
package com.tlb.jsoninstance;

import java.util.ArrayList;

import com.tlb.entity.Category;
import com.tlb.entity.Flower;
import com.tlb.entity.Order;
import com.tlb.entity.Tip;

public class HomeInstance {
	private int status_no;//保存状态码
	private int request_time;
	private ArrayList<Category> categories;//保存鲜花分类
	private ArrayList<Flower> flowers;//保存鲜花
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
