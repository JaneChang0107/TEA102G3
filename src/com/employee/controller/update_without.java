package com.employee.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


@WebServlet("/update_without")
public class update_without extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		StoreService storeService = new StoreService();
		List<StoreVO> store_list = storeService.getAll();
		
		PrintWriter out = res.getWriter();
		
		String enter = readJSONString(req);
		System.out.println(enter);
		
		
		try {
			JSONObject jsonObj = new JSONObject(enter);
			String e_id = jsonObj.getString("e_id");
			String select_store = jsonObj.getString("st_id");
			String e_title = jsonObj.getString("e_title");
			String e_phone = jsonObj.getString("e_phone");
			String e_address = jsonObj.getString("e_address");
			String e_name = jsonObj.getString("e_name");
			String e_gender = jsonObj.getString("e_gender"); 
			String e_identity = jsonObj.getString("e_identity"); 
			String f_date1 = jsonObj.getString("f_date1"); 
			String e_email = jsonObj.getString("e_email"); 
			
			if(e_title == null || e_title.equals("")) {
				e_title = "EMPLOYEE";
			}
			
			System.out.println("select_store = " + select_store);
			
			//時間字串轉SQL date
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDtae = new java.util.Date();
			utilDtae = fmt.parse(f_date1);
			Date SQLDate = new Date(utilDtae.getTime());
			
			System.out.println(SQLDate);
			
			EmployeeVO employeeVO = new EmployeeVO();
			employeeVO.setE_id(e_id);
			employeeVO.setSt_id(select_store);
			employeeVO.setE_title(e_title);
			employeeVO.setE_phone(e_phone);
			employeeVO.setE_address(e_address);
			employeeVO.setE_name(e_name);
			employeeVO.setE_gender(e_gender);
			employeeVO.setE_identity(e_identity);
			employeeVO.setE_birth(SQLDate);
			employeeVO.setE_email(e_email);
			
			EmployeeService employeeService = new EmployeeService();
			employeeVO = employeeService.updateEmployee_without(e_id, e_identity, e_name, e_gender, SQLDate, e_email, e_phone, e_address, e_title, select_store);
			
			System.out.println("lastEmpVO" + employeeVO);
			
			Map<String, Object> data_map = new HashMap<>();
			data_map.put("store", store_list);
			data_map.put("emp", employeeVO);
			
			JSONObject obj = new JSONObject(data_map);
			
			String jsonStr = obj.toString();
			System.out.println("aaaaa" + jsonStr);
			out.write(jsonStr);
			out.flush();
			out.close();
			
//			EmployeeService service = new  EmployeeService();
//			EmployeeVO employeeVO = service.getOneEmployee(e_id);
//			System.out.println("employeeVO = " + employeeVO);
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
