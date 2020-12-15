package com.rentpicture.model;

import java.io.Serializable;
import java.util.Base64;

public class RentPictureVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String rp_id;
	private byte[] rp_picture;
	private String r_id;

	public RentPictureVO() {

	}

	public String getRp_id() {
		return rp_id;
	}

	public void setRp_id(String rp_id) {
		this.rp_id = rp_id;
	}

	public byte[] getRp_picture() {
		return rp_picture;
	}

	public void setRp_picture(byte[] rp_picture) {
		this.rp_picture = rp_picture;
	}

	public String getR_id() {
		return r_id;
	}

	public void setR_id(String r_id) {
		this.r_id = r_id;
	}

	public String getRp_picture2() {
		if(rp_picture!=null) {
			rp_picture = Base64.getEncoder().encode(rp_picture);
			String str = new String(rp_picture);
			return str;
		}
		else {
			return ""; 
		}
	}

}
