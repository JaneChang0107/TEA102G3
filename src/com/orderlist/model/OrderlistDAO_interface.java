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
    //�X�f��
    public void updateStatus(OrderlistVO orderlistVO);
    //��f��
    public void updateStatusArrive(String o_id);
    //�q�槹����
    public void updateStatusFinish(String o_id);
}
