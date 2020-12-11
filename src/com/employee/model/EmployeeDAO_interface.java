package com.employee.model;

import java.util.*;

public interface EmployeeDAO_interface {
	
	 public void insert(EmployeeVO employeeVO);
     public void update(EmployeeVO employeeVO);
     public void delete(String employeeno);
     public EmployeeVO findByPrimaryKey(String employeeno);
     public List<EmployeeVO> getAll();
     //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//     public List<EmployeeVO> getAll(Map<String, String[]> map); 

}
