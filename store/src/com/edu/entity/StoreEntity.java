package com.edu.entity;

public class StoreEntity {
	private String s_id;
	private String s_name;
	private String s_price;
	private String s_picture;
	
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_price() {
		return s_price;
	}
	public void setS_price(String s_price) {
		this.s_price = s_price;
	}
	public String getS_picture() {
		return s_picture;
	}
	public void setS_picture(String s_picture) {
		this.s_picture = s_picture;
	}
	
	@Override
	public String toString() {
		return "s_id = " + s_id + 
				"s_name = " + s_name + 
				"s_price = " + s_price + 
				"s_picture" + s_price;
	}
}
