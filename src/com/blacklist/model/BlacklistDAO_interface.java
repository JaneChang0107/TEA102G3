package com.blacklist.model;

import java.util.List;

public interface BlacklistDAO_interface {
    public void insert(BlacklistVO blacklistVO);
    public void update(BlacklistVO blacklistVO);
    public void delete(String bl_id);
    public BlacklistVO findByPrimaryKey(String bl_id);
    public List<BlacklistVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
