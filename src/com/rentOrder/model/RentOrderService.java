package com.rentOrder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;



public class RentOrderService {
	private RentOrderDAO_interface dao;

	public RentOrderService() {
		dao = new RentOrderJDBCDAO();
	}

	public RentOrderVO addEmployee(Timestamp ro_date, String ro_status, String st_id, Timestamp ro_outdate,
			Timestamp ro_backdate, Integer ro_deposit, Integer ro_total, byte[] ro_sign, Integer ro_pm, String m_id) {

		RentOrderVO rentOrderVO = new RentOrderVO();

		rentOrderVO.setRo_date(ro_date);
		rentOrderVO.setRo_status(ro_status);
		rentOrderVO.setSt_id(st_id);
		rentOrderVO.setRo_outdate(ro_outdate);
		rentOrderVO.setRo_backdate(ro_backdate);
		rentOrderVO.setRo_deposit(ro_deposit);
		rentOrderVO.setRo_total(ro_total);
		rentOrderVO.setRo_sign(ro_sign);
		rentOrderVO.setRo_pm(ro_pm);
		rentOrderVO.setM_id(m_id);
		
		dao.insert(rentOrderVO);

		return rentOrderVO;
	}

	public RentOrderVO updateEmployee(Timestamp ro_date, String ro_status, String st_id, Timestamp ro_outdate,
			Timestamp ro_backdate, Integer ro_deposit, Integer ro_total, byte[] ro_sign, Integer ro_pm, String m_id) {

		RentOrderVO rentOrderVO = new RentOrderVO();

		rentOrderVO.setRo_date(ro_date);
		rentOrderVO.setRo_status(ro_status);
		rentOrderVO.setSt_id(st_id);
		rentOrderVO.setRo_outdate(ro_outdate);
		rentOrderVO.setRo_backdate(ro_backdate);
		rentOrderVO.setRo_deposit(ro_deposit);
		rentOrderVO.setRo_total(ro_total);
		rentOrderVO.setRo_sign(ro_sign);
		rentOrderVO.setRo_pm(ro_pm);
		rentOrderVO.setM_id(m_id);
		dao.update(rentOrderVO);

		return rentOrderVO;
	}

	public void deleteRentOrder(String rentOrderVO) {
		dao.delete(rentOrderVO);
	}

	public RentOrderVO getOneEmployee(String rentOrderVO) {
		return dao.findByPrimaryKey(rentOrderVO);
	}

	public List<RentOrderVO> getAll() {
		return dao.getAll();
	}
}
