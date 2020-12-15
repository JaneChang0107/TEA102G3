package com.rent.model;

import java.util.List;

public interface RentDAO_interface {
	
	public void insert(RentVO rentVO);
	public void update(RentVO rentVO);
	public void delete(String r_id);
	public RentVO findByPrimaryKey(String r_id);
	public List<RentVO> getAll();

}
