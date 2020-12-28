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

	// �i�ˬd�ϥΪ̿�J���b��(e_id) �K�X(e_password)�O�_���ġj
	// �i��ڤW���ܸ�Ʈw�j�M���j
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

//---------------------------------------------�n�J---------------------------------------------
		
		if ("login".equals(action)) {
//�ŧi���~�T�����X
			List<String> errorMsgs = new LinkedList<String>();
//����~�T���s�Jsession scope, �ݵy���getAttribute()����
			HttpSession session = req.getSession();
			session.setAttribute("errorMsgs", errorMsgs);
			
			String str = req.getParameter("e_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("�п�J���u�s��");
			}
		
// �p�G���~�T�����O�Ū�, �h���_�{�����^���� 
			if (!errorMsgs.isEmpty()) {
				res.sendRedirect(req.getContextPath() + "/Back_end/employee/login.jsp");
				return;//�{�����_
			}
		
		

// �i���o�ϥΪ� �b��(e_id) �K�X(e_password)�j
			String e_id = req.getParameter("e_id");
			String e_password = req.getParameter("e_password");

// �i�ˬd�ӱb�� , �K�X�O�_���ġj
			if (!allowUser(e_id, e_password)) { // �i�b�� , �K�X�L�Įɡj
	
				res.sendRedirect(req.getContextPath() + "/Back_end/employee/login.jsp");
			} else { // �i�b�� , �K�X���Į�, �~���H�U�u�@�j
//				HttpSession session = req.getSession();
				session.setAttribute("e_id", e_id); // *�u�@1: �~�bsession�����w�g�n�J�L������
	
				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location"); // *�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}
				
				/***************************2.�}�l�d�߸��****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(e_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				session.setAttribute("employeeVO", employeeVO);
				System.out.println(employeeVO);
//�s���Ӧ������ �ǳƵn�J
				res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp"); // *�u�@3: (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
			}
			
			
		}
		
//---------------------------------------------��s---------------------------------------------
		
			if ("update_without".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			HttpSession session = req.getSession();
			session.setAttribute("errorMsgs", errorMsgs);
		
			try {
				System.out.println("update");
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String e_id =req.getParameter("e_id").trim();								
				
				String e_identity = req.getParameter("e_identity");
				String e_identityReg = "^[A-Z]{1}[1-2]{1}[0-9]{8}$";
				if (e_identity == null || e_identity.trim().length() == 0) {
					errorMsgs.add("�����Ҧr��: �ФŪť�");
				} else if(!e_identity.trim().matches(e_identityReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�п�J���T�����Ү榡");
				}
				
				
				String e_name = req.getParameter("e_name");
				String e_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (e_name == null || e_name.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				} else if(!e_name.trim().matches(e_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���u�m�W: �u��O���B�^��r��, �B���ץ��ݦb2��10����");
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
					errorMsgs.add("�q��: �u��O�Ʀr , �B����09�}�Y , ���׬�10");
				}
				
				String e_address = req.getParameter("e_address");
				String e_addressReg = "^[(\u4e00-\u9fa5)(0-9)]{5,30}$";
				if (e_address == null || e_address.trim().length() == 0) {
					errorMsgs.add("��}: �ФŪť�");
				} else if(!e_address.trim().matches(e_addressReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("��}: �u��O����B�Ʀr , �B���ץ��ݦb5��30����");
				}
				
				String e_title = req.getParameter("e_title").trim();
				if (e_gender == null || e_gender.trim().length() == 0) {
					errorMsgs.add("�п��¾��");
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
				if (!errorMsgs.isEmpty()) {					
					session.setAttribute("employeeVO", employeeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
					System.out.println("���� �L�T��");
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/Back_end/employee/update_employee_input.jsp");
//					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeVO = employeeSvc.updateEmployee_without(e_id, e_identity, e_name, e_gender,e_birth, e_email, e_phone, e_address, e_title, st_id);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
				System.out.println("succeess");
//				String url = "/Back_end/employee/listOneEmployee.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
//				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				res.sendRedirect(req.getContextPath() + "/Back_end/employee/index_backstage.jsp");
				System.out.println("��L���~");
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/Back_end/employee/update_employee_input.jsp");
//				failureView.forward(req, res);
			}
		}
	}
}