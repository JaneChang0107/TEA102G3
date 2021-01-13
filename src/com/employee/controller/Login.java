package com.employee.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;

import javax.servlet.annotation.WebServlet;
import com.employee.model.*;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 【檢查使用者輸入的帳號(e_id) 密碼(e_password)是否有效】
	// 【實際上應至資料庫搜尋比對】
	protected boolean allowUser(String e_id, String e_password) {
		EmployeeService employeeSvc = new EmployeeService();
		EmployeeVO employeeVO = employeeSvc.getOneEmployee(e_id);
		
		try{
			if (employeeVO.getE_id().equals(e_id) && employeeVO.getE_password().equals(e_password))
			return true;
		else
			return false;
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		
		String action = req.getParameter("action");

//---------------------------------------------登入---------------------------------------------
		
		if ("login".equals(action)) {
//宣告錯誤訊息集合
			List<String> errorMsgs_login = new LinkedList<String>();
//把錯誤訊息存入session scope, 待稍後用getAttribute()取用
			HttpSession session = req.getSession();
			session.setAttribute("errorMsgs_login", errorMsgs_login);
			
			String str = req.getParameter("e_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs_login.add("請輸入員工編號");
			} 
			
			EmployeeService employeeSvc = new EmployeeService();
			EmployeeVO employeeVO = employeeSvc.getOneEmployee(str);
			
			String password = req.getParameter("e_password");
			if(!password.equals(employeeVO.getE_password())) {
				errorMsgs_login.add("錯了!滾!");
			}
		
// 如果錯誤訊息不是空的, 則切斷程式轉交回首頁 
			if (!errorMsgs_login.isEmpty()) {
				res.sendRedirect(req.getContextPath() + "/Back_end/employee/login.jsp");
				return;//程式中斷
			}
		
		

// 【取得使用者 帳號(e_id) 密碼(e_password)】
			String e_id = req.getParameter("e_id");
			String e_password = req.getParameter("e_password");

// 【檢查該帳號 , 密碼是否有效】
			if (!allowUser(e_id, e_password)) { // 【帳號 , 密碼無效時】
	
				res.sendRedirect(req.getContextPath() + "/Back_end/employee/login.jsp");
			} else { // 【帳號 , 密碼有效時, 才做以下工作】
//				HttpSession session = req.getSession();
				session.setAttribute("e_id", e_id); // *工作1: 才在session內做已經登入過的標識
	
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}
				
				
				/***************************2.開始查詢資料****************************************/
//				EmployeeService employeeSvc = new EmployeeService();
//				EmployeeVO employeeVO = employeeSvc.getOneEmployee(e_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				session.setAttribute("employeeVO", employeeVO);
				System.out.println(employeeVO);
//存完該死的資料 準備登入
				res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp"); // *工作3: (-->如無來源網頁:則重導至login_success.jsp)
			}
			
			
		}
		
//---------------------------------------------更新部分---------------------------------------------
		
			if ("update_without".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs_without = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			HttpSession session = req.getSession();
			session.setAttribute("errorMsgs_without", errorMsgs_without);
		
			try {
				System.out.println("update");
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String e_id =req.getParameter("e_id").trim();								
				
				String e_identity = req.getParameter("e_identity");
				String e_identityReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
				if (e_identity == null || e_identity.trim().length() == 0) {
					errorMsgs_without.add("身分證字號: 請勿空白");
				} else if(!e_identity.trim().matches(e_identityReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs_without.add("請輸入正確身分證格式");
				}
				
				
				String e_name = req.getParameter("e_name");
				String e_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (e_name == null || e_name.trim().length() == 0) {
					errorMsgs_without.add("員工姓名: 請勿空白");
				} else if(!e_name.trim().matches(e_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs_without.add("員工姓名: 只能是中、英文字母, 且長度必需在2到10之間");
	            }
				
				String e_gender = req.getParameter("e_gender").trim();
				if (e_gender == null || e_gender.trim().length() == 0) {
					errorMsgs_without.add("請選擇性別");
				}
				
				java.sql.Date e_birth = null;
				try {
					e_birth = java.sql.Date.valueOf(req.getParameter("e_birth").trim());
				} catch (IllegalArgumentException e) {
					e_birth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs_without.add("請輸入日期!");
				}
				
				String e_email = req.getParameter("e_email");
				String e_emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
				if (e_email == null || e_email.trim().length() == 0) {
					errorMsgs_without.add("電子信箱: 請勿空白");
				} else if(!e_email.trim().matches(e_emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs_without.add("電子信箱: 請依照電子郵件格式輸入");
	            }
				
				String e_phone = req.getParameter("e_phone");
				String e_phoneReg = "^09[0-9]{8}$";
				if (e_phone == null || e_phone.trim().length() == 0) {
					errorMsgs_without.add("電話: 請勿空白");
				} else if(!e_phone.trim().matches(e_phoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs_without.add("電話: 只能是數字 , 且必需09開頭 , 長度為10");
				}
				
				String e_address = req.getParameter("e_address");
				String e_addressReg = "^[(\u4e00-\u9fa5)(0-9)]{5,30}$";
				if (e_address == null || e_address.trim().length() == 0) {
					errorMsgs_without.add("住址: 請勿空白");
				} else if(!e_address.trim().matches(e_addressReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs_without.add("住址: 只能是中文、數字 , 且長度必需在5到30之間");
				}
				
				String e_title = req.getParameter("e_title").trim();
				if (e_gender == null || e_gender.trim().length() == 0) {
					errorMsgs_without.add("請選擇職稱");
				}
			
				
				String st_id =req.getParameter("st_id").trim();



				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setE_id(e_id);
				employeeVO.setE_identity(e_identity);
				employeeVO.setE_name(e_name);
				employeeVO.setE_gender(e_gender);
				employeeVO.setE_birth(e_birth);
				employeeVO.setE_email(e_email);
				employeeVO.setE_phone(e_phone);
				employeeVO.setE_address(e_address);
				employeeVO.setE_title(e_title);
				employeeVO.setSt_id(st_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs_without.isEmpty()) {					
					session.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
					System.out.println("有錯 印訊息");
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/Back_end/employee/update_employee_input.jsp");
//					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeVO = employeeSvc.updateEmployee_without(e_id, e_identity, e_name, e_gender,e_birth, e_email, e_phone, e_address, e_title, st_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
				System.out.println("succeess");
//				String url = "/Back_end/employee/index_backstage.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs_without.add("修改資料失敗:"+e.getMessage());
				res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
				System.out.println("其他錯誤");
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/Back_end/employee/update_employee_input.jsp");
//				failureView.forward(req, res);
			}
		}
//---------------------------------------------更新密碼---------------------------------------------
			
			if ("update_pwd".equals(action)) { // 來自update_emp_input.jsp的請求
				
				List<String> errorMsgs_pwd = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				HttpSession session = req.getSession();
				session.setAttribute("errorMsgs_pwd", errorMsgs_pwd);
				
				try {
					System.out.println("update_pwd");
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String e_id =req.getParameter("e_id").trim();
					
					String new_password = req.getParameter("new_password");
					String check_password = req.getParameter("check_password");
					
					String e_password = req.getParameter("e_password");
					String e_passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{6,30}$";
					if (e_password == null || e_password.trim().length() == 0) {
						errorMsgs_pwd.add("員工密碼: 請勿空白");
					} else if(!e_password.trim().matches(e_passwordReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs_pwd.add("員工密碼: 只能是英文字母、數字和_ , 且長度必需在6到30之間");
					}
					
					EmployeeService employeeSvc = new EmployeeService();
					EmployeeVO employeeVO1 = employeeSvc.getOneEmployee(e_id);
					
//進行舊密碼和資料庫比對,相符才進行修改
					if(!employeeVO1.getE_password().equals(e_password)) {
						errorMsgs_pwd.add("原始密碼不相符");
					} else if(!new_password.equals(check_password)) {
						errorMsgs_pwd.add("請確認第二次密碼與第一次相符");
					}

					EmployeeVO employeeVO = new EmployeeVO();
					employeeVO.setE_id(e_id);
					employeeVO.setE_password(new_password);
					
					System.out.println(new_password);
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs_pwd.isEmpty()) {					
						session.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
						res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
						System.out.println("有錯 印訊息");
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/Back_end/employee/update_employee_input.jsp");
//					failureView.forward(req, res);
						return; //程式中斷
					}						
						/***************************2.開始修改資料*****************************************/
						employeeVO = employeeSvc.updateEmployee_pwd(e_id, new_password);
						
						/***************************3.修改完成,準備轉交(Send the Success view)*************/
						req.setAttribute("employeeVO", employeeVO); // 資料庫update成功後,正確的的empVO物件,存入req
						res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
						System.out.println("succeess");
//				String url = "/Back_end/employee/listOneEmployee.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
										
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs_pwd.add("修改資料失敗:"+e.getMessage());
					res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
					System.out.println("其他錯誤");
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/Back_end/employee/update_employee_input.jsp");
//				failureView.forward(req, res);
				}
			}
//---------------------------------------------更新單筆---------------------------------------------			
			if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
	            System.out.println("getOne");
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
					/***************************1.接收請求參數****************************************/
					String e_id = req.getParameter("e_id");
					System.out.println("e_id");
					
					/***************************2.開始查詢資料****************************************/
					EmployeeService employeeSvc = new EmployeeService();
					EmployeeVO employeeVO = employeeSvc.getOneEmployee(e_id);
									
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.setAttribute("employeeVO", employeeVO);         // 資料庫取出的empVO物件,存入req
					String url = "/Back_end/employee/updateEmployee.jsp";
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
//---------------------------------------------更改狀態---------------------------------------------
			if ("update_status".equals(action)) { // 來自update_emp_input.jsp的請求
				
				List<String> errorMsgs_status = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				HttpSession session = req.getSession();
				session.setAttribute("errorMsgs_status", errorMsgs_status);
				Integer new_status = new Integer(0); 
						 
				try {
					System.out.println("update_status");
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String e_id =req.getParameter("e_id").trim();
					
					Integer e_status =  new Integer(req.getParameter("e_status"));
					

					EmployeeVO employeeVO = new EmployeeVO();
					employeeVO.setE_id(e_id);
					employeeVO.setE_status(e_status);
					
					System.out.println(e_id + "123");
					System.out.println(e_status);
					
					
					
					switch(e_status) {
						case 0:
							new_status = 1;
							break;
						case 1:
							new_status = 0;
							break;
					}
					
					System.out.println("new_status = " + new_status);
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs_status.isEmpty()) {					
						session.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
						res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
						System.out.println("有錯 印訊息");
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/Back_end/employee/update_employee_input.jsp");
//					failureView.forward(req, res);
						return; //程式中斷
					}						
						/***************************2.開始修改資料*****************************************/
						EmployeeService employeeSvc = new EmployeeService();
						employeeVO = employeeSvc.updateEmployee_status(e_id, new_status); //e_status這裡要改
						
						/***************************3.修改完成,準備轉交(Send the Success view)*************/
						req.setAttribute("employeeVO", employeeVO); // 資料庫update成功後,正確的的empVO物件,存入req
						res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
						System.out.println("succeess");
//				String url = "/Back_end/employee/listOneEmployee.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
										
					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs_status.add("修改資料失敗:"+e.getMessage());
					res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
					System.out.println("其他錯誤");
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/Back_end/employee/update_employee_input.jsp");
//				failureView.forward(req, res);
				}
			}
//---------------------------------------------新增---------------------------------------------
			if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				System.out.println("action");
				List<String> errorMsgs_new = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				HttpSession session = req.getSession();
				session.setAttribute("errorMsgs_new", errorMsgs_new);

				try {
					System.out.println("insert");
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String e_password = req.getParameter("e_password");
					String e_passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{6,30}$";
					if (e_password == null || e_password.trim().length() == 0) {
						errorMsgs_new.add("員工密碼: 請勿空白");
					} else if(!e_password.trim().matches(e_passwordReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs_new.add("員工密碼: 只能是英文字母、數字和_ , 且長度必需在6到30之間");
					}
					
					String e_identity = req.getParameter("e_identity");
					String e_identityReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
					if (e_identity == null || e_identity.trim().length() == 0) {
						errorMsgs_new.add("身分證字號: 請勿空白");
					} else if(!e_identity.trim().matches(e_identityReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs_new.add("請輸入正確身分證格式");
					}
					
					
					String e_name = req.getParameter("e_name");
					String e_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
					if (e_name == null || e_name.trim().length() == 0) {
						errorMsgs_new.add("員工姓名: 請勿空白");
					} else if(!e_name.trim().matches(e_nameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs_new.add("員工姓名: 只能是中、英文字母, 且長度必需在2到10之間");
		            }
					
					String e_gender = req.getParameter("e_gender").trim();
					if (e_gender == null || e_gender.trim().length() == 0) {
						errorMsgs_new.add("請選擇性別");
					}
					
					java.sql.Date e_birth = null;
					try {
						e_birth = java.sql.Date.valueOf(req.getParameter("e_birth").trim());
					} catch (IllegalArgumentException e) {
						e_birth=new java.sql.Date(System.currentTimeMillis());
						errorMsgs_new.add("請輸入日期!");
					}
					
					String e_email = req.getParameter("e_email");
					String e_emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
					if (e_email == null || e_email.trim().length() == 0) {
						errorMsgs_new.add("電子信箱: 請勿空白");
					} else if(!e_email.trim().matches(e_emailReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs_new.add("電子信箱: 請依照電子郵件格式輸入");
		            }
					
					String e_phone = req.getParameter("e_phone");
					String e_phoneReg = "^09[0-9]{8}$";
					if (e_phone == null || e_phone.trim().length() == 0) {
						errorMsgs_new.add("電話: 請勿空白");
					} else if(!e_phone.trim().matches(e_phoneReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs_new.add("電話: 只能是數字 , 且必需09開頭 , 長度為10");
					}
					
					String e_address = req.getParameter("e_address");
					String e_addressReg = "^[(\u4e00-\u9fa5)(0-9)]{5,30}$";
					if (e_address == null || e_address.trim().length() == 0) {
						errorMsgs_new.add("住址: 請勿空白");
					} else if(!e_address.trim().matches(e_addressReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs_new.add("住址: 只能是中文、數字 , 且長度必需在5到30之間");
					}
					
					String e_title = req.getParameter("e_title").trim();
					if (e_gender == null || e_gender.trim().length() == 0) {
						errorMsgs_new.add("請選擇職稱");
					}


					Integer e_status = new Integer(req.getParameter("e_status").trim());
									
					
					String st_id =req.getParameter("st_id").trim();

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
					if (!errorMsgs_new.isEmpty()) {
						session.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
						res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
						System.out.println("有錯 印訊息");
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("/Back_end/employee/addEmployee.jsp");
//						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					EmployeeService employeeSvc = new EmployeeService();
					employeeVO = employeeSvc.addEmployee(e_password, e_identity, e_name, e_gender,e_birth, e_email, e_phone, e_address, e_title, e_status, st_id);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					req.setAttribute("employeeVO", employeeVO); // 資料庫update成功後,正確的的empVO物件,存入req
					res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
					System.out.println("succeess");
//					String url = "/Back_end/employee/listAllEmployee.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs_new.add(e.getMessage());
					res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
					System.out.println("其他錯誤");
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/Back_end/employee/addEmployee.jsp");
//					failureView.forward(req, res);
				}
			}
/**************************************以下為ajax**************************************************/
//			String action_ajax = readJSONString(req);
//			System.out.println(action_ajax);
//			if ("ajax_update_without".equals(action_ajax)) {
//				
//			System.out.println("ajax");
//			}
	}
//	
//	//json物件轉string
//	public String readJSONString(HttpServletRequest request){
//		StringBuffer json = new StringBuffer();
//		String line = null;
//		try {
//			BufferedReader reader = request.getReader();
//			while((line = reader.readLine()) != null) {
//				json.append(line);
//			}
//		}	catch(Exception e) {
//			e.printStackTrace();
//		}
//		return json.toString();
//	}
}