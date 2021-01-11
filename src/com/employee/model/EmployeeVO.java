package com.employee.model;

import java.io.Serializable;
import java.sql.Date;

import com.store.model.StoreService;

public class EmployeeVO implements Serializable{
	private String e_id;
	private String e_password;
	private String e_identity;
	private String e_name;
	private String e_gender;
	private Date   e_birth;
	private String e_email;
	private String e_phone;
	private String e_address;
	private String e_title;
	private Integer e_status;
	private String st_id;
	private String e_status_view;
	
	
	public String getE_id() {
		return e_id;
	}
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
	public String getE_password() {
		return e_password;
	}
	public void setE_password(String e_password) {
		this.e_password = e_password;
	}
	public String getE_identity() {
		return e_identity;
	}
	public void setE_identity(String e_identity) {
		this.e_identity = e_identity;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public String getE_gender() {
		return e_gender;
	}
	public void setE_gender(String e_gender) {
		this.e_gender = e_gender;
	}
	public Date getE_birth() {
		return e_birth;
	}
	public void setE_birth(Date e_birth) {
		this.e_birth = e_birth;
	}
	public String getE_email() {
		return e_email;
	}
	public void setE_email(String e_email) {
		this.e_email = e_email;
	}
	public String getE_phone() {
		return e_phone;
	}
	public void setE_phone(String e_phone) {
		this.e_phone = e_phone;
	}
	public String getE_address() {
		return e_address;
	}
	public void setE_address(String e_address) {
		this.e_address = e_address;
	}
	public String getE_title() {
		return e_title;
	}
	public void setE_title(String e_title) {
		this.e_title = e_title;
	}
	public int getE_status() {
		return e_status;
	}
	public void setE_status(int e_status) {
		this.e_status = e_status;
	}
	public String getSt_id() {
		return st_id;
	}
	public String getSt_name() {
		StoreService sService = new StoreService();
		return sService.getOneStore(st_id).getSt_name();
	}
	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}
	
	public String getE_status_view() {
		String e_status_view = ""; 
		if(e_status == 0) {
			e_status_view = "°±Â¾";
		} else if(e_status == 1) {
			e_status_view = "¦bÂ¾";
		}	
		return e_status_view;				
	}
	
}
