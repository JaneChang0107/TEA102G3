package com.viewseller.model;

import java.sql.Timestamp;


public class ViewsellerVO {

	private String v_id;
	private String o_id;
	private String m_buyid;
	private String m_sellid;
	private String v_gb;
	private String v_comment;
	private Timestamp v_date;
	
	public String getV_id() {
		return v_id;
	}
	public void setV_id(String v_id) {
		this.v_id = v_id;
	}
	public String getO_id() {
		return o_id;
	}
	public void setO_id(String o_id) {
		this.o_id = o_id;
	}
	public String getM_buyid() {
		return m_buyid;
	}
	public void setM_buyid(String m_buyid) {
		this.m_buyid = m_buyid;
	}
	public String getM_sellid() {
		return m_sellid;
	}
	public void setM_sellid(String m_sellid) {
		this.m_sellid = m_sellid;
	}
	public String getV_gb() {
		return v_gb;
	}
	public void setV_gb(String v_gb) {
		this.v_gb = v_gb;
	}
	public String getV_comment() {
		return v_comment;
	}
	public void setV_comment(String v_comment) {
		this.v_comment = v_comment;
	}
	public Timestamp getV_date() {
		return v_date;
	}
	public void setV_date(Timestamp v_date) {
		this.v_date = v_date;
	}
	
	
	
}
