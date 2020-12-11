package com.rentOrder.model;

import java.util.List;

import com.rentOrder.model.RentOrderVO;

public interface RentOrderDAO_interface {
	public void insert(RentOrderVO RentOrderVO);
    public void update(RentOrderVO RentOrderVO);
    public void delete(String ro_id);
    public RentOrderVO findByPrimaryKey(String ro_id);
    public List<RentOrderVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<RentOrderVO> getAll(Map<String, String[]> map); 
}
