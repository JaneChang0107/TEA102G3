package com.rentpicture.model;

import java.util.List;


public interface RentPictureDAO_interface {
	
	public void insert(RentPictureVO rentPictureVO);
	public void update(RentPictureVO rentPictureVO);
	public void delete(String rp_id);
	public RentPictureVO findByPrimaryKey(String rp_id);
	public List<RentPictureVO> getAll();

}
