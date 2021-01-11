package com.employee.model;

import java.util.*;

public interface EmployeeDAO_interface {
	
	 public void insert(EmployeeVO employeeVO);
     public void update(EmployeeVO employeeVO);
     public void update_without(EmployeeVO employeeVO);
     public void update_pwd(EmployeeVO employeeVO);
     public void update_status(EmployeeVO employeeVO);
     public void delete(String e_id);
     public EmployeeVO findByPrimaryKey(String e_id);
     public EmployeeVO findByEmail(String e_email);
     public List<EmployeeVO> findByPrimaryKey_e_name(String e_name);
     public EmployeeVO getEmployeePwd(String e_id);
     public List<EmployeeVO> getAll();
     //萬用複合查詢(傳入參數型態Map)(回傳 List)
//     public List<EmployeeVO> getAll(Map<String, String[]> map); 

}
