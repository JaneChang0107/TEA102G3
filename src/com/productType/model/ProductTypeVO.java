package com.productType.model;

public class ProductTypeVO {

	private String pt_id;
	private String pt_platform;
	private String pt_kind;
	
	
	
	
	@Override
	public String toString() {
		return "ProductTypeVO [pt_id=" + pt_id + ", pt_platform=" + pt_platform + ", pt_kind=" + pt_kind + "]";
	}
	public String getPt_id() {
		return pt_id;
	}
	public void setPt_id(String pt_id) {
		this.pt_id = pt_id;
	}
	public String getPt_platform() {
		return pt_platform;
	}
	public void setPt_platform(String pt_platform) {
		this.pt_platform = pt_platform;
	}
	public String getPt_kind() {
		return pt_kind;
	}
	public void setPt_kind(String pt_kind) {
		this.pt_kind = pt_kind;
	}
	
	
}
