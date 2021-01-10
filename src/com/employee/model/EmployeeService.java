package com.employee.model;

import java.sql.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;


public class EmployeeService {
	private EmployeeDAO_interface dao;

	public EmployeeService() {
		dao = new EmployeeJDBCDAO();
	}

	public EmployeeVO addEmployee(String e_password, String e_identity, String e_name, String e_gender, java.sql.Date e_birth, 
			String e_email, String e_phone, String e_address, String e_title, int e_status, String st_id) {

		EmployeeVO employeeVO = new EmployeeVO();

		employeeVO.setE_password(e_password);
		employeeVO.setE_identity(e_identity);
		employeeVO.setE_name(e_name);
		employeeVO.setE_gender(e_gender);
		employeeVO.setE_birth(e_birth);
		employeeVO.setE_email(e_email);
		employeeVO.setE_phone(e_phone);
		employeeVO.setE_address(e_address);
		employeeVO.setE_title(e_title);
		employeeVO.setE_status(e_status);
		employeeVO.setSt_id(st_id);
		
		dao.insert(employeeVO);

		return employeeVO;
	}

	public EmployeeVO updateEmployee(String e_id, String e_password, String e_identity, String e_name, String e_gender, java.sql.Date e_birth, 
			String e_email, String e_phone, String e_address, String e_title, int e_status, String st_id) {

		EmployeeVO employeeVO = new EmployeeVO();
		
		employeeVO.setE_id(e_id);
		employeeVO.setE_password(e_password);
		employeeVO.setE_identity(e_identity);
		employeeVO.setE_name(e_name);
		employeeVO.setE_gender(e_gender);
		employeeVO.setE_birth(e_birth);
		employeeVO.setE_email(e_email);
		employeeVO.setE_phone(e_phone);
		employeeVO.setE_address(e_address);
		employeeVO.setE_title(e_title);
		employeeVO.setE_status(e_status);
		employeeVO.setSt_id(st_id);
		dao.update(employeeVO);

		return employeeVO;
	}
	
	public EmployeeVO updateEmployee_without(String e_id, String e_identity, String e_name, String e_gender, java.sql.Date e_birth, 
			String e_email, String e_phone, String e_address, String e_title, String st_id) {
		
		EmployeeVO employeeVO = new EmployeeVO();		
		employeeVO.setE_id(e_id);		
		employeeVO.setE_identity(e_identity);
		employeeVO.setE_name(e_name);
		employeeVO.setE_gender(e_gender);
		employeeVO.setE_birth(e_birth);
		employeeVO.setE_email(e_email);
		employeeVO.setE_phone(e_phone);
		employeeVO.setE_address(e_address);
		employeeVO.setE_title(e_title);		
		employeeVO.setSt_id(st_id);
		dao.update_without(employeeVO);
		
		return employeeVO;
	}
	
	public EmployeeVO updateEmployee_pwd(String e_id, String e_password) {
		
		EmployeeVO employeeVO = new EmployeeVO();		
		employeeVO.setE_id(e_id);		
		employeeVO.setE_password(e_password);
		dao.update_pwd(employeeVO);
		
		return employeeVO;
	}
	
	public EmployeeVO updateEmployee_status(String e_id, int e_status) {
		
		EmployeeVO employeeVO = new EmployeeVO();		
		employeeVO.setE_id(e_id);		
		employeeVO.setE_status(e_status);
		dao.update_status(employeeVO);
		
		return employeeVO;
	}

	public void deleteEmployee(String employeeVO) {
		dao.delete(employeeVO);
	}

	public EmployeeVO getOneEmployee(String employeeVO) {
		return dao.findByPrimaryKey(employeeVO);
	}
	public List<EmployeeVO> getOneEmployee_e_name(String employeeVO) {
		return dao.findByPrimaryKey_e_name(employeeVO);
	}
	public EmployeeVO getOneEmployeePwd(String employeeVO) {
		return dao.getEmployeePwd(employeeVO);
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}

}
