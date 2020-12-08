package com.product.model;

public class ProductVO {
	
	private String p_id;
	private String p_name;
	private Integer p_price;
	private String p_detail;
	private String pt_id;
	private Integer p_count;
	private java.sql.Timestamp p_addDate;
	private java.sql.Timestamp p_reviseDate;
	private Integer p_status;
	private String m_id;
	
	
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public Integer getP_price() {
		return p_price;
	}
	public void setP_price(Integer p_price) {
		this.p_price = p_price;
	}
	public String getP_detail() {
		return p_detail;
	}
	public void setP_detail(String p_detail) {
		this.p_detail = p_detail;
	}
	public String getPt_id() {
		return pt_id;
	}
	public void setPt_id(String pt_id) {
		this.pt_id = pt_id;
	}
	public Integer getP_count() {
		return p_count;
	}
	public void setP_count(Integer p_count) {
		this.p_count = p_count;
	}
	public java.sql.Timestamp getP_addDate() {
		return p_addDate;
	}
	public void setP_addDate(java.sql.Timestamp p_addDate) {
		this.p_addDate = p_addDate;
	}
	public java.sql.Timestamp getP_reviseDate() {
		return p_reviseDate;
	}
	public void setP_reviseDate(java.sql.Timestamp p_reviseDate) {
		this.p_reviseDate = p_reviseDate;
	}
	public Integer getP_status() {
		return p_status;
	}
	public void setP_status(Integer p_status) {
		this.p_status = p_status;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	
}
