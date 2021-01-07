package com.employee.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/ajax")
public class ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		StoreService sevice = new StoreService();
		String action_ajax = readJSONString(req);
		System.out.println("action_ajax = " + action_ajax);
		PrintWriter out= res.getWriter();
		
		List<StoreVO> store_list = sevice.getAll();
		System.out.println(store_list);
		
		try {			
			JSONObject jsonObj = new JSONObject(action_ajax);
//			System.out.println("jsonObj =" + jsonObj);
			String e_id =  jsonObj.getString("e_id");	
			System.out.println("e_id = " + e_id);
			EmployeeService employeeSvc = new EmployeeService();
			EmployeeVO employeeVO = employeeSvc.getOneEmployee(e_id);
//			System.out.println("e_id = " + e_id);
			System.out.println("employeeVO = " + employeeVO);
		
			Map<String, Object> data_map = new HashMap<>();
			data_map.put("store", store_list);
			data_map.put("emp", employeeVO);
			
			JSONObject Obj =  new JSONObject(data_map);
		
			String jsonstr = Obj.toString();
			System.out.println(jsonstr);			
			out.write(jsonstr);
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
