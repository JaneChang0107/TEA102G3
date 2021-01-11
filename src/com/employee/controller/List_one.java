package com.employee.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;


@WebServlet("/List_one")
public class List_one extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = res.getWriter();
		EmployeeService service = new EmployeeService();
		
		String enter = readJSONString(req);
		try {
			JSONObject jsonObj = new JSONObject(enter);
			String e_name =  jsonObj.getString("input");
//			System.out.println("e_name = " + e_name);
			
		List<EmployeeVO> employeeVO =  service.getOneEmployee_e_name(e_name);
		

		
		JSONArray jsonArray = new JSONArray(employeeVO);
		String jsonStr = jsonArray.toString();
		out.println(jsonStr);
		out.flush();
		out.close();
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
