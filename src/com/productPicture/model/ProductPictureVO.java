package com.productPicture.model;

import java.util.Base64;

public class ProductPictureVO implements java.io.Serializable {
	
	private String pp_id;
	private byte[] pp_picture;
	private String p_id;
	
	
	public String getPp_id() {
		return pp_id;
	}
	public void setPp_id(String pp_id) {
		this.pp_id = pp_id;
	}
	public byte[] getPp_picture() {
		return pp_picture;
	}
	public void setPp_picture(byte[] pp_picture) {
		this.pp_picture = pp_picture;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getPp_picture64() {
		if(pp_picture != null) {
			pp_picture = Base64.getEncoder().encode(pp_picture);
			String picture = new String(pp_picture);
			return picture;
		}
		return "";
	}
	

}
