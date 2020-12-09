package com.orderlist.model;

import java.util.List;


public interface OrderlistDAO_interface {
	public void insert(OrderlistVO orderlistVO);
	public void update(OrderlistVO orderlistVO);
	public void delete(String orderlistVO);
	public OrderlistVO findByPrimaryKey(String orderlistVO);
    public List<OrderlistVO> getAll();
}
