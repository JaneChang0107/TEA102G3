package com.bankcode.model;

public class BankcodeVO implements java.io.Serializable{

	private String b_code;
	private String b_name;
	
	
	@Override
	public String toString() {
		return "bankcodeBean [b_code=" + b_code + ", b_name=" + b_name + "]";
	}
	public String getB_code() {
		return b_code;
	}
	public void setB_code(String string) {
		this.b_code = string;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public BankcodeVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
