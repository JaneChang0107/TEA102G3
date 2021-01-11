package com.employee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;


@WebServlet("/Forget_password")
public class Forget_password extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String e_email = req.getParameter("e_email");
		
		EmployeeService service = new EmployeeService();
		EmployeeVO employeeVO = service.getOneEmployee_email(e_email);
		String e_id = employeeVO.getE_id();
		
		String e_password = service.random();
		
		service.updateEmployee_pwd(e_id, e_password);
	}
	
	
}
