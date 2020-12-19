package com.rentDetail.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.rentDetail.model.RentDetailService;
import com.rentDetail.model.RentDetailVO;

@WebServlet("/rentDetail/RentDetailServlet")
public class RentDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//��ܳ浧
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("rd_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentDetail/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String rd_id = null;
				try {
					rd_id = str;
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentDetail/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				RentDetailService rentDetailSvc = new RentDetailService();
				RentDetailVO rentDetailVO = rentDetailSvc.getOneRentDetail(rd_id);
				if (rentDetailVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentDetail/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("rentDetailVO", rentDetailVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/rentDetail/listOneRentDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentDetail/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
//��s�浧		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String rd_id = req.getParameter("rd_id");
				
				/***************************2.�}�l�d�߸��****************************************/
				RentDetailService rentDetailSvc = new RentDetailService();
				RentDetailVO rentDetailVO = rentDetailSvc.getOneRentDetail(rd_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("rentDetailVO", rentDetailVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/rentDetail/update_rentDetail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentDetail/listAllRentDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String rd_id = req.getParameter("rd_id").trim();
				
				
				String ro_id = req.getParameter("ro_id").trim();

				String r_id = req.getParameter("r_id").trim();

				Integer rd_count = null;
				try {
					rd_count = new Integer(req.getParameter("rd_count").trim());
				} catch (NumberFormatException e) {
					rd_count = 0;
					errorMsgs.add("�����ж�Ʀr.");
				}

				RentDetailVO rentDetailVO = new RentDetailVO();
				rentDetailVO.setRd_id(rd_id);
				rentDetailVO.setRo_id(ro_id);
				rentDetailVO.setR_id(r_id);
				rentDetailVO.setRd_count(rd_count);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentDetailVO", rentDetailVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentDetail/update_rentDetail_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				RentDetailService rentDetailSvc = new RentDetailService();
				rentDetailVO = rentDetailSvc.updateRentDetail(rd_id, ro_id, r_id, rd_count);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("rentDetailVO", rentDetailVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/Back_end/rentDetail/listOneRentDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentDetail/update_rentDetail_input.jsp");
				failureView.forward(req, res);
			}
		}
//�s�W
        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String ro_id = req.getParameter("ro_id").trim();

				String r_id = req.getParameter("r_id").trim();

				Integer rd_count = null;
				try {
					rd_count = new Integer(req.getParameter("rd_count").trim());
				} catch (NumberFormatException e) {
					rd_count = 0;
					errorMsgs.add("�����ж�Ʀr.");
				}

				RentDetailVO rentDetailVO = new RentDetailVO();
				rentDetailVO.setRo_id(ro_id);
				rentDetailVO.setR_id(r_id);
				rentDetailVO.setRd_count(rd_count);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentDetailVO", rentDetailVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentDetail/addRentDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				System.out.println("1");
				RentDetailService rentDetailSvc = new RentDetailService();
				System.out.println("2");
				rentDetailVO = rentDetailSvc.addRentDetail(ro_id, r_id, rd_count);
				System.out.println("3");
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				System.out.println("4");
				String url = "/Back_end/rentDetail/listAllRentDetail.jsp";
				System.out.println("5");
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				System.out.println("6");
				successView.forward(req, res);				
				System.out.println("7");
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				System.out.println("8");
				errorMsgs.add(e.getMessage());
				System.out.println("9");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentDetail/addRentDetail.jsp");
				System.out.println("10");
				failureView.forward(req, res);
				System.out.println("11");
			}
		}
		
//�R��		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String rd_id =req.getParameter("rd_id");
				
				/***************************2.�}�l�R�����***************************************/
				RentDetailService rentDetailSvc = new RentDetailService();
				rentDetailSvc.deleteRentDetail(rd_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/Back_end/rentDetail/listAllRentDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentDetail/listAllRentDetail.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
