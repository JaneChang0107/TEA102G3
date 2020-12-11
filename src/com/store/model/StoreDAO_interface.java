package com.store.model;

import java.util.List;

public interface StoreDAO_interface {
    public void insert(StoreVO storeVO);
    public void update(StoreVO storeVO);
    public void delete(String st_id);
    public StoreVO findByPrimaryKey(String st_id);
    public List<StoreVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
