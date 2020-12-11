package com.rentDetail.model;

import java.util.*;

public interface RentDetailDAO_interface {
	public void insert(RentDetailVO rentDetailVO);
    public void update(RentDetailVO rentDetailVO);
    public void delete(String rd_id);
    public RentDetailVO findByPrimaryKey(String rd_id);
    public List<RentDetailVO> getAll();
    //�d�߬Y���������u(�@��h)(�^�� Set)
//    public Set<RentDetailVO> getEmpsByDeptno(String rd_id);
}
