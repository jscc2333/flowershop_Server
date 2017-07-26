package com.tlb.jsoninstance;

import java.util.ArrayList;

import com.tlb.entity.Tip;

public class TipInstance {
	private int status_no;
	private ArrayList<Tip> tipList;
	public TipInstance(int status_no, ArrayList<Tip> tipList) {
		super();
		this.status_no = status_no;
		this.tipList = tipList;
	}
	public int getStatus_no() {
		return status_no;
	}
	public void setStatus_no(int status_no) {
		this.status_no = status_no;
	}
	public ArrayList<Tip> getTipList() {
		return tipList;
	}
	public void setTipList(ArrayList<Tip> tipList) {
		this.tipList = tipList;
	}
	
}
