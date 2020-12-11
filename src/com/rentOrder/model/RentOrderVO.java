package com.rentOrder.model;

import java.sql.Timestamp;

public class RentOrderVO {
	private String ro_id;
	private Timestamp ro_date;
	private String ro_status;
	private String st_id;
	private Timestamp ro_outdate;
	private Timestamp ro_backdate;
	private Integer ro_deposit;
	private Integer ro_total;
	private byte[] ro_sign;
	private Integer ro_pm;
	private String m_id;

	public RentOrderVO() {
	}

	public String getRo_id() {
		return ro_id;
	}

	public void setRo_id(String ro_id) {
		this.ro_id = ro_id;
	}

	public Timestamp getRo_date() {
		return ro_date;
	}

	public void setRo_date(Timestamp ro_date) {
		this.ro_date = ro_date;
	}

	public String getRo_status() {
		return ro_status;
	}

	public void setRo_status(String ro_status) {
		this.ro_status = ro_status;
	}

	public String getSt_id() {
		return st_id;
	}

	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}

	public Timestamp getRo_outdate() {
		return ro_outdate;
	}

	public void setRo_outdate(Timestamp ro_outdate) {
		this.ro_outdate = ro_outdate;
	}

	public Timestamp getRo_backdate() {
		return ro_backdate;
	}

	public void setRo_backdate(Timestamp ro_backdate) {
		this.ro_backdate = ro_backdate;
	}

	public Integer getRo_deposit() {
		return ro_deposit;
	}

	public void setRo_deposit(Integer ro_deposit) {
		this.ro_deposit = ro_deposit;
	}

	public Integer getRo_total() {
		return ro_total;
	}

	public void setRo_total(Integer ro_total) {
		this.ro_total = ro_total;
	}

	public byte[] getRo_sign() {
		return ro_sign;
	}

	public void setRo_sign(byte[] ro_sign) {
		this.ro_sign = ro_sign;
	}

	public Integer getRo_pm() {
		return ro_pm;
	}

	public void setRo_pm(Integer ro_pm) {
		this.ro_pm = ro_pm;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

}
