package com.employee.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.employee.model.EmployeeService;


@WebServlet("/change_status")
public class change_status extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		String enter = readJSONString(req);
		
		try {
			JSONObject jsonObj = new JSONObject(enter);
			String e_id = jsonObj.getString("e_id");
			String e_status = jsonObj.getString("e_status");
			
			int num_e_status = Integer.parseInt(e_status);
			
			if(num_e_status == 0) {
				num_e_status = 1;
			} else {
				num_e_status = 0;
			}
			
			EmployeeService service = new EmployeeService();
			service.updateEmployee_status(e_id, num_e_status);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	/***********************json物件轉string方法***********************/
	public String readJSONString(HttpServletRequest request){
		StringBuffer json = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while((line = reader.readLine()) != null) {
				json.append(line);
			}
		}	catch(Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}

}
