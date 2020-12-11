package com.orderlist.model;

import java.sql.Timestamp;


public class OrderlistVO {
 private String o_id;
 private Timestamp o_date;
 private String o_status;
 private Timestamp o_shipdate;
 private Timestamp o_deceiptdate;
 private Timestamp o_finishdate;
 private String o_transport;
 private String o_address;
 private Integer o_total;
 private Integer o_pm;
 private String m_id;
 
public String getO_id() {
	return o_id;
}
public void setO_id(String o_id) {
	this.o_id = o_id;
}
public Timestamp getO_date() {
	return o_date;
}
public void setO_date(Timestamp o_date) {
	this.o_date = o_date;
}
public String getO_status() {
	return o_status;
}
public void setO_status(String o_status) {
	this.o_status = o_status;
}
public Timestamp getO_shipdate() {
	return o_shipdate;
}
public void setO_shipdate(Timestamp o_shipdate) {
	this.o_shipdate = o_shipdate;
}
public Timestamp getO_deceiptdate() {
	return o_deceiptdate;
}
public void setO_deceiptdate(Timestamp o_deceiptdate) {
	this.o_deceiptdate = o_deceiptdate;
}
public Timestamp getO_finishdate() {
	return o_finishdate;
}
public void setO_finishdate(Timestamp o_finishdate) {
	this.o_finishdate = o_finishdate;
}
public String getO_transport() {
	return o_transport;
}
public void setO_transport(String o_transport) {
	this.o_transport = o_transport;
}
public String getO_address() {
	return o_address;
}
public void setO_address(String o_address) {
	this.o_address = o_address;
}
public Integer getO_total() {
	return o_total;
}
public void setO_total(Integer o_total) {
	this.o_total = o_total;
}
public Integer getO_pm() {
	return o_pm;
}
public void setO_pm(Integer o_pm) {
	this.o_pm = o_pm;
}
public String getM_id() {
	return m_id;
}
public void setM_id(String m_id) {
	this.m_id = m_id;
}
 
 
}
