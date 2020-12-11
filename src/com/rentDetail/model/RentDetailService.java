package com.rentDetail.model;

import java.util.List;

import com.employee.model.EmployeeDAO_interface;
import com.employee.model.EmployeeJDBCDAO;
import com.employee.model.EmployeeVO;

public class RentDetailService {
	private RentDetailDAO_interface dao;

	public RentDetailService() {
		dao = new RentDetailJDBCDAO();
	}

	public RentDetailVO addEmployee(String rd_id, String ro_id, String r_id, Integer r_count) {

		RentDetailVO rentDetailVO = new RentDetailVO();

		rentDetailVO.setRo_id(ro_id);
		rentDetailVO.setR_id(r_id);
		rentDetailVO.setRd_count(r_count);
		
		dao.insert(rentDetailVO);

		return rentDetailVO;
	}

	public RentDetailVO updateEmployee(String rd_id, String ro_id, String r_id, Integer r_count) {

		RentDetailVO rentDetailVO = new RentDetailVO();

		rentDetailVO.setRo_id(ro_id);
		rentDetailVO.setR_id(r_id);
		rentDetailVO.setRd_count(r_count);
		dao.update(rentDetailVO);

		return rentDetailVO;
	}

	public void deleteRentDetail(String rentDetailVO) {
		dao.delete(rentDetailVO);
	}

	public RentDetailVO getOneRentDetail(String rentDetailVO) {
		return dao.findByPrimaryKey(rentDetailVO);
	}

	public List<RentDetailVO> getAll() {
		return dao.getAll();
	}

}
