package com.bell.model;

import java.io.Serializable;

import com.orderlist.model.OrderlistVO;

public class BellVO implements Serializable{
	private String m_id;
	private OrderlistVO orderlist;
	private String message;
	
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public OrderlistVO getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(OrderlistVO orderlist) {
		this.orderlist = orderlist;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
