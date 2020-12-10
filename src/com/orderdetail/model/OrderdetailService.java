package com.orderdetail.model;

import java.util.List;

public class OrderdetailService {
	private OrderdetailDAO_interface dao;
	
	public OrderdetailService() {
		dao = new OrderdetailJDBCDAO();
		
	}
	
	public OrderdetailVO addOrderdetail(String o_id,String p_id,Integer od_count) {
		OrderdetailVO ODVO = new OrderdetailVO();
		ODVO.setO_id(o_id);
		ODVO.setP_id(p_id);
		ODVO.setOd_count(od_count);
		dao.insert(ODVO);
		return ODVO;
	}
	
	public OrderdetailVO updateOrderdetail(String od_id,String o_id,String p_id,Integer od_count) {
		OrderdetailVO ODVO = new OrderdetailVO();
		ODVO.setOd_id(od_id);
		ODVO.setO_id(o_id);
		ODVO.setP_id(p_id);
		ODVO.setOd_count(od_count);
		dao.update(ODVO);
		return ODVO;
	}
	
	public void deleteOrderdetail(String od_id) {
		dao.delete(od_id);
	}
	
	public OrderdetailVO getOneOrderdetail(String od_id) {
		return dao.findByPrimaryKey(od_id);
	}
	
	public List<OrderdetailVO> getAll(){
		return dao.getAll();
	}
	
	
	
	
}
