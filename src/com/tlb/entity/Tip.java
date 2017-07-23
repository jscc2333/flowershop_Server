package com.tlb.entity;

public class Tip {
	private Integer tipID;
	private String title;
	private String text;
	private String imgsrc;

	public Tip(int tipID, String title, String text, String imgsrc) {
		super();
		this.tipID = tipID;
		this.title = title;
		this.text = text;
		this.imgsrc = imgsrc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImgsrc() {
		return imgsrc;
	}

	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}

	public Integer getTipID() {
		return tipID;
	}

	public void setTipID(Integer tipID) {
		this.tipID = tipID;
	}

}
