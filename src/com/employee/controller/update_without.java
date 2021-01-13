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
		
		EmployeeService employeeService = new EmployeeService();
		
		
		StoreService storeService = new StoreService();
		List<StoreVO> store_list = storeService.getAll();
		List<String> errorMsgs = new LinkedList<String>();
		
		PrintWriter out = res.getWriter();
		
		String enter = readJSONString(req);
		System.out.println(enter);
		
		
		try {
			JSONObject jsonObj = new JSONObject(enter);
			String e_id = jsonObj.getString("e_id");
			EmployeeVO employeeVO1 =  employeeService.getOneEmployee(e_id);
			if(!(employeeVO1.getE_id()).equals(e_id)) {
				errorMsgs.add("員工id錯誤");
			}
			
			String select_store = jsonObj.getString("st_id");
//			String st_idReg = "(^ST)([0]{4})[1-5]";
			if(!(employeeVO1.getSt_id()).equals(select_store)) {
				errorMsgs.add("門市錯誤");
			}
			
			String e_title = jsonObj.getString("e_title");
			if(!(employeeVO1.getE_title()).equals(e_title)) { 
				errorMsgs.add("職稱錯誤");
			}
			
			String e_phone = jsonObj.getString("e_phone");
			String e_phoneReg = "^09[0-9]{8}$";
			if (e_phone == null || e_phone.trim().length() == 0) {
				errorMsgs.add("電話: 請勿空白");
			} else if(!e_phone.trim().matches(e_phoneReg)) { 
				errorMsgs.add("電話: 只能是數字 , 且必需09開頭 , 長度為10");
			}
			
			String e_address = jsonObj.getString("e_address");
			String e_addressReg = "^[(\u4e00-\u9fa5)(0-9)]{5,30}$";
			if (e_address == null || e_address.trim().length() == 0) {
				errorMsgs.add("住址: 請勿空白");
			} else if(!e_address.trim().matches(e_addressReg)) { 
				errorMsgs.add("住址: 只能是中文、數字 , 且長度必需在5到30之間");
			}
			
			String e_name = jsonObj.getString("e_name");
			String e_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";

			if (e_name == null || e_name.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if(!e_name.trim().matches(e_nameReg)) { 
				errorMsgs.add("員工姓名: 只能是中、英文字母, 且長度必需在2到10之間");
            }
			
			String e_gender = jsonObj.getString("e_gender");
			String e_genderReg = "^[(0-1)]{1}$";
			if (e_gender == null || e_gender.trim().length() == 0) {
				errorMsgs.add("職稱: 請勿空白");
			} else if(!e_gender.trim().matches(e_genderReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("職稱: 0或1");
			}
			
			String e_identity = jsonObj.getString("e_identity");
			String e_identityReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
			if (e_identity == null || e_identity.trim().length() == 0) {
				errorMsgs.add("身分證字號: 請勿空白");
			} else if(!e_identity.trim().matches(e_identityReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("請輸入正確身分證格式");
			}
			
			String f_date1 = jsonObj.getString("f_date1"); 
			
			String e_email = jsonObj.getString("e_email");
			String e_emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
			if (e_email == null || e_email.trim().length() == 0) {
				errorMsgs.add("電子信箱: 請勿空白");
			} else if(!e_email.trim().matches(e_emailReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("電子信箱: 請依照電子郵件格式輸入");
            }
			
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
			
			
			if(errorMsgs != null) {
				employeeVO = employeeService.updateEmployee_without(e_id, e_identity, e_name, e_gender, SQLDate, e_email, e_phone, e_address, e_title, select_store);
			}
			
			System.out.println("lastEmpVO" + employeeVO);
			
			Map<String, Object> data_map = new HashMap<>();
			data_map.put("store", store_list);
			data_map.put("emp", employeeVO);
			if(errorMsgs != null) {
				data_map.put("errorMsg", errorMsgs);
			}
			
			JSONObject obj = new JSONObject(data_map);
			
			String jsonStr = obj.toString();
//			System.out.println("aaaaa" + jsonStr);
			out.write(jsonStr);
			out.flush();
			out.close();
			
//			EmployeeService service = new  EmployeeService();
//			EmployeeVO employeeVO = service.getOneEmployee(e_id);
//			System.out.println("employeeVO = " + employeeVO);
			
			
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
