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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				String str = req.getParameter("e_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String e_id = null;
				try {
					e_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(e_id);
				if (employeeVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/employee/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD
            System.out.println("getone");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String e_id = new String(req.getParameter("e_id"));
				System.out.println("e_id");
				
				/***************************2.�}�l�d�߸��****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(e_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("employeeVO", employeeVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/employee/update_employee_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				System.out.println("update");
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String e_id =req.getParameter("e_id").trim();
								
				String e_password = req.getParameter("e_password");
				String e_passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_password == null || e_password.trim().length() == 0) {
					errorMsgs.add("���u�K�X: �ФŪť�");
				} else if(!e_password.trim().matches(e_passwordReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���u�K�X: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				String e_identity = req.getParameter("e_identity");
				String e_identityReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_identity == null || e_identity.trim().length() == 0) {
					errorMsgs.add("�����Ҧr��: �ФŪť�");
				} else if(!e_identity.trim().matches(e_identityReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�����Ҧr��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				
				String e_name = req.getParameter("e_name");
				String e_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_name == null || e_name.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				} else if(!e_name.trim().matches(e_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				String e_gender = req.getParameter("e_gender").trim();
				if (e_gender == null || e_gender.trim().length() == 0) {
					errorMsgs.add("�п�ܩʧO");
				}
				
				java.sql.Date e_birth = null;
				try {
					e_birth = java.sql.Date.valueOf(req.getParameter("e_birth").trim());
				} catch (IllegalArgumentException e) {
					e_birth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				String e_email = req.getParameter("e_email");
				String e_emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
				if (e_email == null || e_email.trim().length() == 0) {
					errorMsgs.add("�q�l�H�c: �ФŪť�");
				} else if(!e_email.trim().matches(e_emailReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�q�l�H�c: �Ш̷ӹq�l�l��榡��J");
	            }
				
				String e_phone = req.getParameter("e_phone");
				String e_phoneReg = "^09[0-9]{8}$";
				if (e_phone == null || e_phone.trim().length() == 0) {
					errorMsgs.add("�q��: �ФŪť�");
				} else if(!e_phone.trim().matches(e_phoneReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�q��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				String e_address = req.getParameter("e_address");
				String e_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_address == null || e_address.trim().length() == 0) {
					errorMsgs.add("��}: �ФŪť�");
				} else if(!e_address.trim().matches(e_addressReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("��}: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				String e_title = req.getParameter("e_title");
				String e_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_title == null || e_title.trim().length() == 0) {
					errorMsgs.add("¾��: �ФŪť�");
				} else if(!e_title.trim().matches(e_titleReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("¾��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
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
					req.setAttribute("employeeVO", employeeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/employee/update_employee_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeVO = employeeSvc.updateEmployee(e_id, e_password, e_identity, e_name, e_gender,e_birth, e_email, e_phone, e_address, e_title, e_status, st_id);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/Back_end/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/employee/update_employee_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			System.out.println("action");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("insert");
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String e_password = req.getParameter("e_password");
				String e_passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_password == null || e_password.trim().length() == 0) {
					errorMsgs.add("���u�K�X: �ФŪť�");
				} else if(!e_password.trim().matches(e_passwordReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���u�K�X: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				String e_identity = req.getParameter("e_identity");
				String e_identityReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_identity == null || e_identity.trim().length() == 0) {
					errorMsgs.add("�����Ҧr��: �ФŪť�");
				} else if(!e_identity.trim().matches(e_identityReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�����Ҧr��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				
				String e_name = req.getParameter("e_name");
				String e_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_name == null || e_name.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				} else if(!e_name.trim().matches(e_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���u�m�W: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				String e_gender = req.getParameter("e_gender").trim();
				if (e_gender == null || e_gender.trim().length() == 0) {
					errorMsgs.add("�п�ܩʧO");
				}
				
				java.sql.Date e_birth = null;
				try {
					e_birth = java.sql.Date.valueOf(req.getParameter("e_birth").trim());
				} catch (IllegalArgumentException e) {
					e_birth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
							
				
				String e_email = req.getParameter("e_email");
				String e_emailReg = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
				if (e_email == null || e_email.trim().length() == 0) {
					errorMsgs.add("�q�l�H�c: �ФŪť�");
				} else if(!e_email.trim().matches(e_emailReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�q�l�H�c: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				String e_phone = req.getParameter("e_phone");
				String e_phoneReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_phone == null || e_phone.trim().length() == 0) {
					errorMsgs.add("�q��: �ФŪť�");
				} else if(!e_phone.trim().matches(e_phoneReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�q��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				String e_address = req.getParameter("e_address");
				String e_addressReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_address == null || e_address.trim().length() == 0) {
					errorMsgs.add("�a�}: �ФŪť�");
				} else if(!e_address.trim().matches(e_addressReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�a�}: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				String e_title = req.getParameter("e_title");
				String e_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (e_title == null || e_title.trim().length() == 0) {
					errorMsgs.add("¾��: �ФŪť�");
				} else if(!e_title.trim().matches(e_titleReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("¾��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				Integer e_status = null;
				try {
					e_status = new Integer(req.getParameter("e_status").trim());
				} catch (NumberFormatException e) {
					e_status = 0;
					errorMsgs.add("�ж񪬺A.");
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
					req.setAttribute("employeeVO", employeeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/employee/addEmployee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeVO = employeeSvc.addEmployee(e_password, e_identity, e_name, e_gender,e_birth, e_email, e_phone, e_address, e_title, e_status, st_id);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/Back_end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/employee/addEmployee.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				System.out.println("delete");
				/***************************1.�����ШD�Ѽ�***************************************/
				String e_id = new String(req.getParameter("e_id"));
				System.out.println("1");
				/***************************2.�}�l�R�����***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				System.out.println("2");
				employeeSvc.deleteEmployee(e_id);
				System.out.println("3");
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/Back_end/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
