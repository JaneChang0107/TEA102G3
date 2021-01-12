package com.orderlist.model;

import java.util.List;


public interface OrderlistDAO_interface {
	public String insert(OrderlistVO orderlistVO);
	public void update(OrderlistVO orderlistVO);
	public void delete(String orderlistVO);
	public OrderlistVO findByPrimaryKey(String orderlistVO);
    public List<OrderlistVO> getAll();
    public List<OrderlistVO> status();
    public List<OrderlistVO> findByMember(String m_id);
    //出貨用
    public void updateStatus(OrderlistVO orderlistVO);
    //到貨用
//    public void updateStatusArrive(OrderlistVO orderlistVO);
//    //訂單完成用
//    public void updateStatusFinish(OrderlistVO orderlistVO);
}
