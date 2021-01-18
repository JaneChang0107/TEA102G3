package com.viewseller.model;

import java.sql.Timestamp;
import java.util.List;

public class ViewsellerService {
	private ViewsellerDAO_interface dao;
	
	public ViewsellerService() {
		dao = new ViewsellerJDBCDAO();
	}
	
	public ViewsellerVO addViewsellerVO(String o_id ,String m_buyid, String m_sellid,String v_gb ,String v_comment, Timestamp v_date) {
		ViewsellerVO viewsellerVO = new ViewsellerVO();
		viewsellerVO.setO_id(o_id);
		viewsellerVO.setM_buyid(m_buyid);
		viewsellerVO.setM_sellid(m_sellid);
		viewsellerVO.setV_gb(v_gb);
		viewsellerVO.setV_comment(v_comment);
		viewsellerVO.setV_date(v_date);
		dao.insert(viewsellerVO);
		
		return viewsellerVO;
	}
	
	public ViewsellerVO updateViewsellerVO(String v_id ,String o_id ,String m_buyid, String m_sellid,String v_gb ,String v_comment, Timestamp v_date) {
		ViewsellerVO viewsellerVO = new ViewsellerVO();
		viewsellerVO.setV_id(v_id);
		viewsellerVO.setO_id(o_id);
		viewsellerVO.setM_buyid(m_buyid);
		viewsellerVO.setM_sellid(m_sellid);
		viewsellerVO.setV_gb(v_gb);
		viewsellerVO.setV_comment(v_comment);
		viewsellerVO.setV_date(v_date);
		dao.update(viewsellerVO);
		
		return viewsellerVO;
	}
	
	public void deleteViewseller(String v_id) {
		dao.delete(v_id);
	}
	
	public ViewsellerVO getOneViewseller(String v_id) {
		return dao.findByPrimaryKey(v_id);
	}
	public ViewsellerVO getOneViewbyoid(String o_id) {
		return dao.getOneViewbyoid(o_id);
	}
	
	public List<ViewsellerVO> searchDate(String m_id,String time1, String time2){
	         return dao.searchDate(m_id,time1,time2);
	}
	public List<ViewsellerVO> findBysellid(String m_sellid) {
		return dao.findBysellid(m_sellid);
	}

	
	public List<ViewsellerVO> getAll(){
		return dao.getAll();
		
	}
}
