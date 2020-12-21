package com.viewseller.model;

import java.sql.Timestamp;
import java.util.List;

public class ViewsellerService {
	private ViewsellerDAO_interface dao;
	
	public ViewsellerService() {
		dao = new ViewsellerJDBCDAO();
	}
	
	public ViewsellerVO addViewsellerVO(String o_id ,String m_buyid, String m_sellid,String v_gb ,String v_comment, Timestamp v_date) {
		ViewsellerVO VVO = new ViewsellerVO();
		VVO.setO_id(o_id);
		VVO.setM_buyid(m_buyid);
		VVO.setM_sellid(m_sellid);
		VVO.setV_gb(v_gb);
		VVO.setV_comment(v_comment);
		VVO.setV_date(v_date);
		dao.insert(VVO);
		
		return VVO;
	}
	
	public ViewsellerVO updateViewsellerVO(String v_id ,String o_id ,String m_buyid, String m_sellid,String v_gb ,String v_comment, Timestamp v_date) {
		ViewsellerVO VVO = new ViewsellerVO();
		VVO.setV_id(v_id);
		VVO.setO_id(o_id);
		VVO.setM_buyid(m_buyid);
		VVO.setM_sellid(m_sellid);
		VVO.setV_gb(v_gb);
		VVO.setV_comment(v_comment);
		VVO.setV_date(v_date);
		dao.update(VVO);
		
		return VVO;
	}
	
	public void deleteViewseller(String v_id) {
		dao.delete(v_id);
	}
	
	public ViewsellerVO getOneViewseller(String v_id) {
		return dao.findByPrimaryKey(v_id);
	}
	
	public List<ViewsellerVO> getAll(){
		return dao.getAll();
		
	}
	
	
}
