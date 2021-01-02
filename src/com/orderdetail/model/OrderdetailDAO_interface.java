package com.orderdetail.model;

import java.util.List;


public interface OrderdetailDAO_interface {
	public void insert(OrderdetailVO orderdetailVO);
	public void update(OrderdetailVO orderdetailVO);
	public void delete(String orderdetailVO);
	public OrderdetailVO findByPrimaryKey(String orderdetailVO);
    public List<OrderdetailVO> getAll();
	public List<OrderdetailVO> count();
	
	
	public List<OrderdetailVO> getDetailByOrder(String o_id);
	
	
}
