package com.blacklist.model;

import java.util.List;

public interface BlacklistDAO_interface {
    public void insert(BlacklistVO blacklistVO);
    public void update(BlacklistVO blacklistVO);
    public void delete(String bl_id);
    public BlacklistVO findByPrimaryKey(String bl_id);
    public List<BlacklistVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
