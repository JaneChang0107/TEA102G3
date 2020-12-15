package com.rent.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class RentVO implements Serializable {

	private static final long serialVersionUID = 1L;

	public RentVO() {

	}
	
	private String r_id;
	private String r_type;
	private String r_name;
	private String pt_id;
	private String r_describe;
	private String r_situation;
	private String r_status;
	private Integer r_price;
	private Timestamp r_adddate;
	private Timestamp r_revisedate;
	private String e_addid;
	private String e_editorid;
	private String st_id;

	public String getR_id() {
		return r_id;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	public String getPt_id() {
		return pt_id;
	}
	public void setPt_id(String pt_id) {
		this.pt_id = pt_id;
	}
	public String getR_type() {
		return r_type;
	}
	public void setR_type(String r_type) {
		this.r_type = r_type;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public String getR_describe() {
		return r_describe;
	}
	public void setR_describe(String r_describe) {
		this.r_describe = r_describe;
	}
	public String getR_situation() {
		return r_situation;
	}
	public void setR_situation(String r_situation) {
		this.r_situation = r_situation;
	}
	public String getR_status() {
		return r_status;
	}
	public void setR_status(String r_status) {
		this.r_status = r_status;
	}
	public Integer getR_price() {
		return r_price;
	}
	public void setR_price(Integer r_price) {
		this.r_price = r_price;
	}
	public String getE_addid() {
		return e_addid;
	}
	public void setE_addid(String e_addid) {
		this.e_addid = e_addid;
	}
	public String getE_editorid() {
		return e_editorid;
	}
	public void setE_editorid(String e_editorid) {
		this.e_editorid = e_editorid;
	}
	public Timestamp getR_revisedate() {
		return r_revisedate;
	}
	public void setR_revisedate(Timestamp r_revisedate) {
		this.r_revisedate = r_revisedate;
	}
	public String getSt_id() {
		return st_id;
	}
	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}
	public Timestamp getR_adddate() {
		return r_adddate;
	}
	public void setR_adddate(Timestamp r_adddate) {
		this.r_adddate = r_adddate;
	}
	
	
	
}