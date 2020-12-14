package com.coupon.model;

import java.util.List;

public interface CouponDAO_interface {

    public void insert(CouponVO couponVO);
    public void update(CouponVO couponVO);
    public void delete(String b_code);
    public CouponVO findByPrimaryKey(String co_id);
    public List<CouponVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}