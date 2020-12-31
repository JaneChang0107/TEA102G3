package com.product.model;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

import com.productType.model.ProductTypeService;
import com.productType.model.ProductTypeVO;

public class ProductVO implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return "ProductVO [p_id=" + p_id + ", p_name=" + p_name + ", p_price=" + p_price + ", p_detail=" + p_detail
				+ ", pt_id=" + pt_id + ", p_count=" + p_count + ", p_addDate=" + p_addDate + ", p_reviseDate="
				+ p_reviseDate + ", p_status=" + p_status + ", m_id=" + m_id + "]";
	}
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
	public String getPt_idName() {
		ProductTypeService ptService = new ProductTypeService();
		ProductTypeVO ptVO = ptService.getOneProductType(pt_id);
		return ptVO.getPt_platform() + ptVO.getPt_kind();
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
	public String getP_addDateSec() {
		Date d = new Date(p_addDate.getTime());
		Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = f.format(d);
		return date;
	}
	public void setP_addDate(java.sql.Timestamp p_addDate) {
		this.p_addDate = p_addDate;
	}
	public java.sql.Timestamp getP_reviseDate() {
		return p_reviseDate;
	}
	public String getP_reviseDateSec() {
		if(p_reviseDate != null) {
			Date d = new Date(p_reviseDate.getTime());
			Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = f.format(d);
			return date;
		}
		return "";
	}
	public void setP_reviseDate(java.sql.Timestamp p_reviseDate) {
		this.p_reviseDate = p_reviseDate;
	}
	public Integer getP_status() {
		return p_status;
	}
	public String getP_statusMeans() {
		String means = "";
		switch(p_status) {
			case 0 :
				means = "已售出";
				break;
			case 1 :
				means = "上架中";
				break;
			case 2 :
				means = "下架中";
				break;
			case 5 :
				means = "待出貨";
				break;
		}
		
		return means;
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
