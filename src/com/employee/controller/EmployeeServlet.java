package com.employee.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.employee.model.*;

@WebServlet("/employee/controller/employeeServlet.do")
public class EmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String str = req.getParameter("e_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String e_id = null;
				try {
					e_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(e_id);
				if (employeeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/employee/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
            System.out.println("getone");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String e_id = new String(req.getParameter("e_id"));
				System.out.println("e_id");
				
				/***************************2.開始查詢資料****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(e_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("employeeVO", employeeVO);         // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/employee/update_employee_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				System.out.println("update");
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String e_id =req.getParameter("e_id").trim();
								
				String e_password = req.getParameter("e_password");
				String e_passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_password == null || e_password.trim().length() == 0) {
					errorMsgs.add("員工密碼: 請勿空白");
				} else if(!e_password.trim().matches(e_passwordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工密碼: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String e_identity = req.getParameter("e_identity");
				String e_identityReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_identity == null || e_identity.trim().length() == 0) {
					errorMsgs.add("身分證字號: 請勿空白");
				} else if(!e_identity.trim().matches(e_identityReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("身分證字號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				
				String e_name = req.getParameter("e_name");
				String e_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_name == null || e_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!e_name.trim().matches(e_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String e_gender = req.getParameter("e_gender").trim();
				if (e_gender == null || e_gender.trim().length() == 0) {
					errorMsgs.add("請選擇性別");
				}
				
				java.sql.Date e_birth = null;
				try {
					e_birth = java.sql.Date.valueOf(req.getParameter("e_birth").trim());
				} catch (IllegalArgumentException e) {
					e_birth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String e_email = req.getParameter("e_email");
				String e_emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
				if (e_email == null || e_email.trim().length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				} else if(!e_email.trim().matches(e_emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電子信箱: 請依照電子郵件格式輸入");
	            }
				
				String e_phone = req.getParameter("e_phone");
				String e_phoneReg = "^09[0-9]{8}$";
				if (e_phone == null || e_phone.trim().length() == 0) {
					errorMsgs.add("電話: 請勿空白");
				} else if(!e_phone.trim().matches(e_phoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電話: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String e_address = req.getParameter("e_address");
				String e_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_address == null || e_address.trim().length() == 0) {
					errorMsgs.add("住址: 請勿空白");
				} else if(!e_address.trim().matches(e_addressReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("住址: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String e_title = req.getParameter("e_title");
				String e_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_title == null || e_title.trim().length() == 0) {
					errorMsgs.add("職稱: 請勿空白");
				} else if(!e_title.trim().matches(e_titleReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("職稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}


				Integer e_status = new Integer(req.getParameter("e_status").trim());
				
				
				String st_id = new String(req.getParameter("st_id").trim());



				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setE_id(e_id);
				employeeVO.setE_password(e_password);
				employeeVO.setE_identity(e_identity);
				employeeVO.setE_name(e_name);
				employeeVO.setE_gender(e_gender);
				employeeVO.setE_birth(e_birth);
				employeeVO.setE_email(e_email);
				employeeVO.setE_phone(e_phone);
				employeeVO.setE_address(e_address);
				employeeVO.setE_title(e_title);
				employeeVO.setE_status(e_status);
				employeeVO.setSt_id(st_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/employee/update_employee_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeVO = employeeSvc.updateEmployee(e_id, e_password, e_identity, e_name, e_gender,e_birth, e_email, e_phone, e_address, e_title, e_status, st_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/employee/update_employee_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			System.out.println("action");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("insert");
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String e_password = req.getParameter("e_password");
				String e_passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_password == null || e_password.trim().length() == 0) {
					errorMsgs.add("員工密碼: 請勿空白");
				} else if(!e_password.trim().matches(e_passwordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工密碼: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String e_identity = req.getParameter("e_identity");
				String e_identityReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_identity == null || e_identity.trim().length() == 0) {
					errorMsgs.add("身分證字號: 請勿空白");
				} else if(!e_identity.trim().matches(e_identityReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("身分證字號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				
				String e_name = req.getParameter("e_name");
				String e_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_name == null || e_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!e_name.trim().matches(e_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String e_gender = req.getParameter("e_gender").trim();
				if (e_gender == null || e_gender.trim().length() == 0) {
					errorMsgs.add("請選擇性別");
				}
				
				java.sql.Date e_birth = null;
				try {
					e_birth = java.sql.Date.valueOf(req.getParameter("e_birth").trim());
				} catch (IllegalArgumentException e) {
					e_birth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
							
				
				String e_email = req.getParameter("e_email");
				String e_emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
				if (e_email == null || e_email.trim().length() == 0) {
					errorMsgs.add("電子信箱: 請勿空白");
				} else if(!e_email.trim().matches(e_emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電子信箱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String e_phone = req.getParameter("e_phone");
				String e_phoneReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_phone == null || e_phone.trim().length() == 0) {
					errorMsgs.add("電話: 請勿空白");
				} else if(!e_phone.trim().matches(e_phoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電話: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String e_address = req.getParameter("e_address");
				String e_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_address == null || e_address.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} else if(!e_address.trim().matches(e_addressReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String e_title = req.getParameter("e_title");
				String e_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_title == null || e_title.trim().length() == 0) {
					errorMsgs.add("職稱: 請勿空白");
				} else if(!e_title.trim().matches(e_titleReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("職稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				Integer e_status = null;
				try {
					e_status = new Integer(req.getParameter("e_status").trim());
				} catch (NumberFormatException e) {
					e_status = 0;
					errorMsgs.add("請填狀態.");
				}

				String st_id = new String(req.getParameter("st_id").trim());

				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setE_password(e_password);
				employeeVO.setE_identity(e_identity);
				employeeVO.setE_name(e_name);
				employeeVO.setE_gender(e_gender);
				employeeVO.setE_birth(e_birth);
				employeeVO.setE_email(e_email);
				employeeVO.setE_phone(e_phone);
				employeeVO.setE_address(e_address);
				employeeVO.setE_title(e_title);
				employeeVO.setE_status(e_status);
				employeeVO.setSt_id(st_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/employee/addEmployee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeVO = employeeSvc.addEmployee(e_password, e_identity, e_name, e_gender,e_birth, e_email, e_phone, e_address, e_title, e_status, st_id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Back_end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/employee/addEmployee.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				System.out.println("delete");
				/***************************1.接收請求參數***************************************/
				String e_id = new String(req.getParameter("e_id"));
				System.out.println("1");
				/***************************2.開始刪除資料***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				System.out.println("2");
				employeeSvc.deleteEmployee(e_id);
				System.out.println("3");
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/Back_end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
