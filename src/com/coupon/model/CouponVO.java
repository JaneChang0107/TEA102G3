package com.coupon.model;

import java.sql.Timestamp;

public class CouponVO implements java.io.Serializable{
	
	private String co_id  ;
	private String co_code;
	private Integer co_amount ;
	private Timestamp co_start  ;
	private Timestamp co_expire;
	private String co_status ;
	public String getCo_id() {
		return co_id;
	}
	public void setCo_id(String co_id) {
		this.co_id = co_id;
	}
	public String getCo_code() {
		return co_code;
	}
	public void setCo_code(String co_code) {
		this.co_code = co_code;
	}
	public Integer getCo_amount() {
		return co_amount;
	}
	public void setCo_amount(Integer co_amount) {
		this.co_amount = co_amount;
	}
	public Timestamp getCo_start() {
		return co_start;
	}
	public void setCo_start(Timestamp co_start) {
		this.co_start = co_start;
	}
	public Timestamp getCo_expire() {
		return co_expire;
	}
	public void setCo_expire(Timestamp co_expire) {
		this.co_expire = co_expire;
	}
	public String getCo_status() {
		return co_status;
	}
	public void setCo_status(String co_status) {
		this.co_status = co_status;
	}
	public CouponVO() {
		super();
		// TODO Auto-generated constructor stub
	}

}