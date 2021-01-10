package com.employee.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;


@WebServlet("/update_pwd")
public class update_pwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
				
		PrintWriter out = res.getWriter();		
		String enter = readJSONString(req);

		HttpSession session = req.getSession();
		String session_e_id = (session.getAttribute("e_id")).toString();
		EmployeeService service = new EmployeeService();
		EmployeeVO employeeVO1 = service.getOneEmployee(session_e_id);
		String origin_password = employeeVO1.getE_password();
		StringBuffer buffer = new StringBuffer();
		
		
		try {
			JSONObject jsonObj = new JSONObject(enter);
			String e_password = jsonObj.getString("e_password");
			String new_password = jsonObj.getString("new_password");
			String check_password = jsonObj.getString("check_password");
			if(e_password.equals("") || new_password.equals("") || check_password.equals("")) {
				buffer.append("請輸入完整");
			} else if (!new_password.equals(check_password)) {
				buffer.append("新密碼與確認密碼不一致");
			} else if(!origin_password.equals(e_password)){
				buffer.append("與原密碼不一致");
			} else if(origin_password.equals(e_password)) {
				
				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setE_id(session_e_id);
				employeeVO.setE_password(new_password);
				
				EmployeeService employeeService = new EmployeeService();
				employeeVO = employeeService.updateEmployee_pwd(session_e_id, new_password);
				buffer.append("success");				
			}
				
			
			String jsonStr = buffer.toString();

			out.write(jsonStr);
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
