package com.orderlist.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderlistService {
	private OrderlistDAO_interface dao;
	
	public OrderlistService() {
		dao= new OrderlistJDBCDAO();
	}
	
	public OrderlistVO addOrderlistVO(Timestamp o_date,String o_status,Timestamp o_shipdate,Timestamp o_deceiptdate,Timestamp o_finishdate,String o_transport,String o_address,Integer o_total,Integer o_pm,String m_id) {
		OrderlistVO OLVO =new OrderlistVO();
		OLVO.setO_date(o_date);
		OLVO.setO_status(o_status);
		OLVO.setO_shipdate(o_shipdate);
		OLVO.setO_deceiptdate(o_deceiptdate);
		OLVO.setO_finishdate(o_finishdate);
		OLVO.setO_transport(o_transport); 
		OLVO.setO_address(o_address);
		OLVO.setO_total(o_total);
		OLVO.setO_pm(o_pm);
		OLVO.setM_id(m_id);
		dao.insert(OLVO);
		
		return OLVO;
	}
	
	public OrderlistVO updateOrderlistVO(String o_id,Timestamp o_date,String o_status,Timestamp o_shipdate,Timestamp o_deceiptdate,Timestamp o_finishdate,String o_transport,String o_address,Integer o_total,Integer o_pm,String m_id) {
		OrderlistVO OLVO =new OrderlistVO();
		OLVO.setO_id(o_id);
		OLVO.setO_date(o_date);
		OLVO.setO_status(o_status);
		OLVO.setO_shipdate(o_shipdate);
		OLVO.setO_deceiptdate(o_deceiptdate);
		OLVO.setO_finishdate(o_finishdate);
		OLVO.setO_transport(o_transport); 
		OLVO.setO_address(o_address);
		OLVO.setO_total(o_total);
		OLVO.setO_pm(o_pm);
		OLVO.setM_id(m_id);
		dao.update(OLVO);
		
		return OLVO;
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
	
	
	
	
	
}
