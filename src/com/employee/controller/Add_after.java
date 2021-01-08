package com.employee.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;


@WebServlet("/Add_after")
public class Add_after extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");		
		PrintWriter out = res.getWriter();
		
		String enter = readJSONString(req);
		
		System.out.println(enter);
		

		try {
//取jsp輸入值塞進資料庫部分
			JSONObject jsonObj = new JSONObject(enter);
			String e_password = jsonObj.getString("e_password");
			String select_store = jsonObj.getString("st_id");
			String e_title = jsonObj.getString("e_title");
			String e_phone = jsonObj.getString("e_phone");
			String e_address = jsonObj.getString("e_address");
			String e_name = jsonObj.getString("e_name");
			String e_gender = jsonObj.getString("e_gender"); 
			String e_identity = jsonObj.getString("e_identity"); 
			String f_date1 = jsonObj.getString("f_date1"); 
			String e_email = jsonObj.getString("e_email");
			String e_status = jsonObj.getString("e_status");
			
			int int_status = Integer.parseInt(e_status);
			
			System.out.println(e_password);
			
			//時間字串轉SQL date
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDtae = new java.util.Date();
			utilDtae = fmt.parse(f_date1);
			Date SQLDate = new Date(utilDtae.getTime());

			//送值去前端前奏
			EmployeeService service = new EmployeeService();	
			
			EmployeeService employeeService = new EmployeeService();
			employeeService.addEmployee(e_password, e_identity, e_name, e_gender, SQLDate, e_email, e_phone, e_address, e_title, int_status, select_store);
			
			List<EmployeeVO> emp_list = service.getAll();
			
//送值去前端顯示部分
			JSONObject sendObj = new JSONObject();
			sendObj.put("emp_list", emp_list);
			System.out.println("sendObj"+sendObj);
			out.println(sendObj);
			out.flush();
			out.close();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
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
