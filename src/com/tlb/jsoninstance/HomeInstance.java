/*
 * ��װ��¼�Ժ������ҳ��ȡ�����ݣ��������ʽ��װ�������Ա���Gson���ý�����ת��Ϊjson��ʽ������ǰ��ʹ��
 */
package com.tlb.jsoninstance;

import java.util.ArrayList;

import com.tlb.entity.Category;
import com.tlb.entity.Flower;
import com.tlb.entity.Order;
import com.tlb.entity.Tip;

public class HomeInstance {
	private int status_no;//����״̬��
	private ArrayList<Category> categories;//�����ʻ�����
	private ArrayList<Flower> flowers;//�����ʻ�
	private ArrayList<Order> orders;
	private ArrayList<Tip> tips;
	public HomeInstance(int status_no, ArrayList<Category> categories,ArrayList<Flower> flowers,
			ArrayList<Order> orders,ArrayList<Tip> tips) {
		super();
		this.status_no = status_no;
		this.categories = categories;
		this.flowers = flowers;
		this.setOrders(orders);
		this.setTips(tips);
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
	public ArrayList<Tip> getTips() {
		return tips;
	}
	public void setTips(ArrayList<Tip> tips) {
		this.tips = tips;
	}
	public ArrayList<Order> getOrders() {
		return orders;
	}
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
}
