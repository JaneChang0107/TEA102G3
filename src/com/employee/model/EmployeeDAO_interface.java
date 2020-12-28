package com.employee.model;

import java.util.*;

public interface EmployeeDAO_interface {
	
	 public void insert(EmployeeVO employeeVO);
     public void update(EmployeeVO employeeVO);
     public void update_without(EmployeeVO employeeVO);
     public void delete(String e_id);
     public EmployeeVO findByPrimaryKey(String e_id);
     public EmployeeVO getEmployeePwd(String e_id);
     public List<EmployeeVO> getAll();
     //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//     public List<EmployeeVO> getAll(Map<String, String[]> map); 

}
