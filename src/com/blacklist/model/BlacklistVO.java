package com.blacklist.model;

public class BlacklistVO implements java.io.Serializable{
	
	private String bl_id;
	private String m_id;
	private String m_blackId;
	
	
	public String getBl_id() {
		return bl_id;
	}
	public void setBl_id(String bl_id) {
		this.bl_id = bl_id;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_blackId() {
		return m_blackId;
	}
	public void setM_blackId(String m_blackId) {
		this.m_blackId = m_blackId;
	}
	public BlacklistVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
