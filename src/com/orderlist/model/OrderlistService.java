package com.orderlist.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderlistService {
	private OrderlistDAO_interface dao;
	
	public OrderlistService() {
		dao= new OrderlistJDBCDAO();
	}
	
	public OrderlistVO addOrderlistVO2(Timestamp o_date,String o_status,String o_transport,String o_address,Integer o_total,Integer o_pm,String m_id) {
		OrderlistVO orderlistVO =new OrderlistVO();
		orderlistVO.setO_date(o_date);
		orderlistVO.setO_status(o_status);
		orderlistVO.setO_transport(o_transport); 
		orderlistVO.setO_address(o_address);
		orderlistVO.setO_total(o_total);
		orderlistVO.setO_pm(o_pm);
		orderlistVO.setM_id(m_id);
		dao.insert(orderlistVO);
		
		return orderlistVO;
	}
	
	
	public OrderlistVO addOrderlistVO(Timestamp o_date,String o_status,Timestamp o_shipdate,Timestamp o_deceiptdate,Timestamp o_finishdate,String o_transport,String o_address,Integer o_total,Integer o_pm,String m_id) {
		OrderlistVO orderlistVO =new OrderlistVO();
		orderlistVO.setO_date(o_date);
		orderlistVO.setO_status(o_status);
		orderlistVO.setO_shipdate(o_shipdate);
		orderlistVO.setO_deceiptdate(o_deceiptdate);
		orderlistVO.setO_finishdate(o_finishdate);
		orderlistVO.setO_transport(o_transport); 
		orderlistVO.setO_address(o_address);
		orderlistVO.setO_total(o_total);
		orderlistVO.setO_pm(o_pm);
		orderlistVO.setM_id(m_id);
		dao.insert(orderlistVO);
		
		return orderlistVO;
	}
	
	public OrderlistVO updateOrderlistVO(String o_id,Timestamp o_date,String o_status,Timestamp o_shipdate,Timestamp o_deceiptdate,Timestamp o_finishdate,String o_transport,String o_address,Integer o_total,Integer o_pm,String m_id) {
		OrderlistVO orderlistVO =new OrderlistVO();
		orderlistVO.setO_id(o_id);
		orderlistVO.setO_date(o_date);
		orderlistVO.setO_status(o_status);
		orderlistVO.setO_shipdate(o_shipdate);
		orderlistVO.setO_deceiptdate(o_deceiptdate);
		orderlistVO.setO_finishdate(o_finishdate);
		orderlistVO.setO_transport(o_transport); 
		orderlistVO.setO_address(o_address);
		orderlistVO.setO_total(o_total);
		orderlistVO.setO_pm(o_pm);
		orderlistVO.setM_id(m_id);
		dao.update(orderlistVO);
		
		return orderlistVO;
	} 
	
	public void deleteOrderlist(String o_id) {
		dao.delete(o_id);
	}
	
	public OrderlistVO getOneOrderlist(String o_id) {
		return dao.findByPrimaryKey(o_id);
	}

	public List<OrderlistVO> getAll(){
		return dao.getAll();
		
	}
	
	public List<OrderlistVO> status(){
		return dao.status();
	}
	
	public List<OrderlistVO> findByMember(String m_id){
		return dao.findByMember(m_id);
	}
	
}
