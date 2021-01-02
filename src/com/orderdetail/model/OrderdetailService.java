package com.orderdetail.model;

import java.util.List;

public class OrderdetailService {
	private OrderdetailDAO_interface dao;
	
	public OrderdetailService() {
		dao = new OrderdetailJDBCDAO();
		
	}
	
	public OrderdetailVO addOrderdetail(String o_id,String p_id,Integer od_count) {
		OrderdetailVO orderdetailVO = new OrderdetailVO();
		orderdetailVO.setO_id(o_id);
		orderdetailVO.setP_id(p_id);
		orderdetailVO.setOd_count(od_count);
		dao.insert(orderdetailVO);
		return orderdetailVO;
	}
	
	public OrderdetailVO updateOrderdetail(String od_id,String o_id,String p_id,Integer od_count) {
		OrderdetailVO orderdetailVO = new OrderdetailVO();
		orderdetailVO.setOd_id(od_id);
		orderdetailVO.setO_id(o_id);
		orderdetailVO.setP_id(p_id);
		orderdetailVO.setOd_count(od_count);
		dao.update(orderdetailVO);
		return orderdetailVO;
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
	
	public List<OrderdetailVO> count(){
		return dao.count();
	}
	
	public List<OrderdetailVO> getDetailByOrder(String o_id){
		return dao.getDetailByOrder(o_id);
	}
	
	
}
