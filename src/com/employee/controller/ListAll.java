package com.employee.controller;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;



@WebServlet("/ListAll")
public class ListAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		EmployeeService service = new EmployeeService();	
		List<EmployeeVO> emp_list = service.getAll();

	
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("emp_list", emp_list);
			System.out.println("jsonObj"+jsonObj);
			out.println(jsonObj);
			out.flush();
			out.close();
		} catch (JSONException e) {
			e.printStackTrace();
		}	
	}

}
