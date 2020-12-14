package com.store.model;

public class StoreVO implements java.io.Serializable{

	private String st_id  ;
	private String st_name;
	private String st_address ;
	
	public String getSt_id() {
		return st_id;
	}
	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}
	public String getSt_name() {
		return st_name;
	}
	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}
	public String getSt_address() {
		return st_address;
	}
	public void setSt_address(String st_address) {
		this.st_address = st_address;
	}
	public StoreVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
