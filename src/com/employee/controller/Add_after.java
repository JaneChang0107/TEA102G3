package com.employee.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
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
		
		List<String> errorMsgs = new LinkedList<String>();
		
//		String enter = readJSONString(req);
		
		
		

		try {
//��jsp��J�ȶ�i��Ʈw����
//			JSONObject jsonObj = new JSONObject(enter);
			String e_password = req.getParameter("e_password");
			if (e_password == null || e_password.trim().length() == 0) {
				errorMsgs.add("�K�X: �ФŪť�");
			}
			String select_store = req.getParameter("st_id");
			String st_idReg = "(^ST)([0]{4})[1-5]";
			if (select_store == null || select_store.trim().length() == 0) {
				errorMsgs.add("�����ФŪť�");
			} else if(!select_store.trim().matches(st_idReg)) { 
				errorMsgs.add("�������~");
			}
			
			String e_title = req.getParameter("e_title");
			String e_titleReg = "(BOSS)|(EMPLOYEE)";
			if (e_title == null || e_title.trim().length() == 0) {
				errorMsgs.add("¾��: �ФŪť�");
			} else if(!e_title.trim().matches(e_titleReg)) { 
				errorMsgs.add("¾��: BOSS��EMPLOYEE");
			}
			
			String e_phone = req.getParameter("e_phone");
			String e_phoneReg = "^09[0-9]{8}$";
			if (e_phone == null || e_phone.trim().length() == 0) {
				errorMsgs.add("�q��: �ФŪť�");
			} else if(!e_phone.trim().matches(e_phoneReg)) { 
				errorMsgs.add("�q��: �u��O�Ʀr , �B����09�}�Y , ���׬�10");
			}
			
			String e_address = req.getParameter("e_address");
			String e_addressReg = "^[(\u4e00-\u9fa5)(0-9)]{5,30}$";
			if (e_address == null || e_address.trim().length() == 0) {
				errorMsgs.add("��}: �ФŪť�");
			} else if(!e_address.trim().matches(e_addressReg)) { 
				errorMsgs.add("��}: �u��O����B�Ʀr , �B���ץ��ݦb5��30����");
			}
			
			String e_name = req.getParameter("e_name");
			String e_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
			if (e_name == null || e_name.trim().length() == 0) {
				errorMsgs.add("���u�m�W: �ФŪť�");
			} else if(!e_name.trim().matches(e_nameReg)) { 
				errorMsgs.add("���u�m�W: �u��O���B�^��r��, �B���ץ��ݦb2��10����");
            }
			
			String e_gender = req.getParameter("e_gender");
			String e_genderReg = "(WOMEN)|(MEN)";
			if (e_gender == null || e_gender.trim().length() == 0) {
				errorMsgs.add("�ʧO: �ФŪť�");
			} else if(!e_gender.trim().matches(e_genderReg)) { 
				errorMsgs.add("�ʧO: MEN or WOMEN");
			}
			
			String e_identity = req.getParameter("e_identity"); 
			String e_identityReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
			if (e_identity == null || e_identity.trim().length() == 0) {
				errorMsgs.add("�����Ҧr��: �ФŪť�");
			} else if(!e_identity.trim().matches(e_identityReg)) { 
				errorMsgs.add("�п�J���T�����Ү榡");
			}
			
			String f_date1 = req.getParameter("e_birth"); 
			String e_email = req.getParameter("e_email");
			String e_emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
			if (e_email == null || e_email.trim().length() == 0) {
				errorMsgs.add("�q�l�H�c: �ФŪť�");
			} else if(!e_email.trim().matches(e_emailReg)) { 
				errorMsgs.add("�q�l�H�c: �Ш̷ӹq�l�l��榡��J");
            }
			
			String e_status = req.getParameter("e_status");
			String e_statusReg = "^[(0-1)]{1}$";
			if (e_status == null || e_status.trim().length() == 0) {
				errorMsgs.add("���A: �ФŪť�");
			} else if(!e_status.trim().matches(e_statusReg)) { 
				errorMsgs.add("���A: 0��1");
			}
//			System.out.println(e_status);
			int int_status = Integer.parseInt(e_status);
			

			
			//�ɶ��r����SQL date
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDtae = new java.util.Date();
			utilDtae = fmt.parse(f_date1);
			Date SQLDate = new Date(utilDtae.getTime());

			//�e�ȥh�e�ݫe��
			EmployeeService service = new EmployeeService();	
			
			EmployeeService employeeService = new EmployeeService();
			
			if(errorMsgs.isEmpty()) {
			employeeService.addEmployee(e_password, e_identity, e_name, e_gender, SQLDate, e_email, e_phone, e_address, e_title, int_status, select_store);
			}
			
			List<EmployeeVO> emp_list = service.getAll();
			
//�e�ȥh�e����ܳ���
			JSONObject sendObj = new JSONObject();
			sendObj.put("emp_list", emp_list);
			if(errorMsgs != null) {
				sendObj.put("errorMsg", errorMsgs);
			}

			out.println(sendObj);
			out.flush();
			out.close();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	
	/***********************json������string��k***********************/
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
