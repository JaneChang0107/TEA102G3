package com.rent.model;

import java.sql.Timestamp;
import java.util.List;

public class RentService {

	private RentDAO_interface dao;

	public RentService() {
		dao = new RentJDBCDAO();
	}

	public RentVO addRent(String r_type, String r_name, String pt_id, String r_describe, String r_situation,
			String r_status, Integer r_price, Timestamp r_adddate, Timestamp r_revisedate, String e_addid,
			String e_editorid, String st_id) {

		RentVO rentVO = new RentVO();

		rentVO.setR_type(r_type);
		rentVO.setR_name(r_name);
		rentVO.setPt_id(pt_id);
		rentVO.setR_describe(r_describe);
		rentVO.setR_situation(r_situation);
		rentVO.setR_status(r_status);
		rentVO.setR_price(r_price);
		rentVO.setR_adddate(r_adddate);
		rentVO.setR_revisedate(r_revisedate);
		rentVO.setE_addid(e_addid);
		rentVO.setE_editorid(e_editorid);
		rentVO.setSt_id(st_id);

		dao.insert(rentVO);

		return rentVO;
	}

	public RentVO updateRent(String r_type, String r_name, String pt_id,String r_describe, String r_situation, String r_status,
			Integer r_price, Timestamp r_adddate,Timestamp r_revisedate, String e_addid,String e_editorid,String st_id,String r_id) {

		RentVO rentVO = new RentVO();

		rentVO.setR_type(r_type);
		rentVO.setR_name(r_name);
		rentVO.setPt_id(pt_id);
		rentVO.setR_describe(r_describe);
		rentVO.setR_situation(r_situation);
		rentVO.setR_status(r_status);
		rentVO.setR_price(r_price);
		rentVO.setR_adddate(r_adddate);
		rentVO.setR_revisedate(r_revisedate);
		rentVO.setE_addid(e_addid);
		rentVO.setE_editorid(e_editorid);
		rentVO.setSt_id(st_id);
		rentVO.setR_id(r_id);
		dao.update(rentVO);

		return rentVO;
	}

	public void deleteRent(String r_id) {
		dao.delete(r_id);
	}

	public RentVO getOneRent(String r_id) {
		return dao.findByPrimaryKey(r_id);
	}

	public List<RentVO> getAll() {
		return dao.getAll();
	}
}
