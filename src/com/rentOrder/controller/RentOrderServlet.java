package com.rentOrder.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.rentOrder.model.RentOrderService;
import com.rentOrder.model.RentOrderVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/rentOrder/RentOrderServlet")
public class RentOrderServlet extends HttpServlet {

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
				String str = req.getParameter("ro_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�q��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentOrder/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String ro_id = null;
				try {
					ro_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentOrder/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				RentOrderService rentOrderSvc = new RentOrderService();
				RentOrderVO rentOrderVO = rentOrderSvc.getOneRentOrder(ro_id);
				if (rentOrderVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentOrder/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("rentOrderVO", rentOrderVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/rentOrder/listOneRentOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentOrder/select_page.jsp");
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
				String ro_id = req.getParameter("ro_id");
				
				/***************************2.�}�l�d�߸��****************************************/
				RentOrderService rentOrderSvc = new RentOrderService();
				RentOrderVO rentOrderVO = rentOrderSvc.getOneRentOrder(ro_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("rentOrderVO", rentOrderVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/rentOrder/update_rentOrder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentOrder/listAllRentOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
//��s
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String ro_id = req.getParameter("ro_id").trim();	
				
				java.sql.Timestamp ro_date = null;
				try {
					ro_date = Timestamp.valueOf(req.getParameter("ro_date").trim());
				} catch (IllegalArgumentException e) {
					ro_date=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				String ro_status = req.getParameter("ro_status").trim();
				if (ro_status == null || ro_status.trim().length() == 0) {
					errorMsgs.add("���u���A�ФŪť�");
				}	
				
				
				String st_id = req.getParameter("st_id").trim();
				if (st_id == null || st_id.trim().length() == 0) {
					errorMsgs.add("�����s���ФŪť�");
				}	
				
				java.sql.Timestamp ro_outdate = null;
				try {
					ro_outdate = Timestamp.valueOf(req.getParameter("ro_outdate").trim());
				} catch (IllegalArgumentException e) {
					ro_outdate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�X�����!");
				}
				
				java.sql.Timestamp ro_backdate = null;
				try {
					ro_backdate = Timestamp.valueOf(req.getParameter("ro_backdate").trim());
				} catch (IllegalArgumentException e) {
					ro_backdate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�k�٤��!");
				}

				Integer ro_deposit = null;
				try {
					ro_deposit = new Integer(req.getParameter("ro_deposit").trim());
				} catch (NumberFormatException e) {
					ro_deposit = 0;
					errorMsgs.add("�����`���B�ж�Ʀr.");
				}
				
				Integer ro_total = null;
				try {
					ro_total = new Integer(req.getParameter("ro_total").trim());
				} catch (NumberFormatException e) {
					ro_total = 0;
					errorMsgs.add("�����`���B�ж�Ʀr.");
				}
				
				RentOrderService rentOrderSvc = new RentOrderService();

				Part ro_sign = req.getPart("ro_sign");
				InputStream in = ro_sign.getInputStream();
				byte[] data = null;
	if(in.available()!=0)	{		
				data = new byte[in.available()];
				in.read(data);
				in.close();
	} else
		data = rentOrderSvc.getOneRentOrder(ro_id).getRo_sign();
	
				
				Integer ro_pm = null;
				try {
					ro_pm = new Integer(req.getParameter("ro_pm").trim());
				} catch (NumberFormatException e) {
					ro_pm = 0;
					errorMsgs.add("�[���ж�Ʀr.");
				}

				String m_id = req.getParameter("m_id").trim();

				RentOrderVO rentOrderVO = new RentOrderVO();
				rentOrderVO.setRo_id(ro_id);
				rentOrderVO.setRo_date(ro_date);
				rentOrderVO.setRo_status(ro_status);
				rentOrderVO.setSt_id(st_id);
				rentOrderVO.setRo_outdate(ro_outdate);
				rentOrderVO.setRo_backdate(ro_backdate);
				rentOrderVO.setRo_deposit(ro_deposit);
				rentOrderVO.setRo_total(ro_total);
				rentOrderVO.setRo_sign(data);
				rentOrderVO.setRo_pm(ro_pm);
				rentOrderVO.setM_id(m_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentOrderVO", rentOrderVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentOrder/update_rentOrder_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				rentOrderVO = rentOrderSvc.updateRentOrder(ro_id, ro_date, ro_status, st_id, ro_outdate,ro_backdate, ro_deposit, ro_total, data, ro_pm, m_id);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("rentOrderVO", rentOrderVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/Back_end/rentOrder/listOneRentOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentOrder/update_rentOrder_input.jsp");
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
				java.sql.Timestamp ro_date = null;
				try {
					ro_date = Timestamp.valueOf(req.getParameter("ro_date").trim());
				} catch (IllegalArgumentException e) {
					ro_date=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				System.out.println("ro_date="+ro_date);
				
				String ro_status = req.getParameter("ro_status").trim();
//				String ro_status = req.getParameter("ro_status");
//				String ro_statusReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ro_status == null || ro_status.trim().length() == 0) {
//					errorMsgs.add("���u���A: �ФŪť�");
//				} else if(!ro_status.trim().matches(ro_statusReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("���u���A: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
//	            }
				
				String st_id = req.getParameter("st_id").trim();
				if (st_id == null || st_id.trim().length() == 0) {
					errorMsgs.add("�����s���ФŪť�");
				}	
				
				java.sql.Timestamp ro_outdate = null;
				try {
					ro_outdate = Timestamp.valueOf(req.getParameter("ro_outdate").trim());
				} catch (IllegalArgumentException e) {
					ro_outdate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�X�����!");
				}
				System.out.println("ro_outdate="+ro_outdate);
				
				java.sql.Timestamp ro_backdate = null;
				try {
					ro_backdate = Timestamp.valueOf(req.getParameter("ro_backdate").trim());
				} catch (IllegalArgumentException e) {
					ro_backdate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�k�٤��!");
				}
				System.out.println("ro_backdate="+ro_backdate);

				Integer ro_deposit = null;
				try {
					ro_deposit = new Integer(req.getParameter("ro_deposit").trim());
				} catch (NumberFormatException e) {
					ro_deposit = 0;
					errorMsgs.add("�����`���B�ж�Ʀr.");
				}
				
				Integer ro_total = null;
				try {
					ro_total = new Integer(req.getParameter("ro_total").trim());
				} catch (NumberFormatException e) {
					ro_total = 0;
					errorMsgs.add("�����`���B�ж�Ʀr.");
				}

				Part ro_sign = req.getPart("ro_sign");
				if (ro_sign == null || ro_sign.getSize() == 0) {
					errorMsgs.add("�ФW�ǹϤ�");
				}
				// obtains input stream of the upload file
				InputStream inputStream = ro_sign.getInputStream();
				byte[] data = new byte[inputStream.available()];
				inputStream.read(data);
				
				Integer ro_pm = null;
				try {
					ro_pm = new Integer(req.getParameter("ro_pm").trim());
				} catch (NumberFormatException e) {
					ro_pm = 0;
					errorMsgs.add("�[���ж�Ʀr.");
				}
				
				String m_id = req.getParameter("m_id").trim();

				RentOrderVO rentOrderVO = new RentOrderVO();
				rentOrderVO.setRo_date(ro_date);
				rentOrderVO.setRo_status(ro_status);
				rentOrderVO.setSt_id(st_id);
				rentOrderVO.setRo_outdate(ro_outdate);
				rentOrderVO.setRo_backdate(ro_backdate);
				rentOrderVO.setRo_deposit(ro_deposit);
				rentOrderVO.setRo_total(ro_total);
				rentOrderVO.setRo_sign(data);
				rentOrderVO.setRo_pm(ro_pm);
				rentOrderVO.setM_id(m_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentOrderVO", rentOrderVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentOrder/addRentOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				RentOrderService rentOrderSvc = new RentOrderService();
				rentOrderVO = rentOrderSvc.addRentOrder(ro_date, ro_status, st_id, ro_outdate,ro_backdate, ro_deposit, ro_total, data, ro_pm, m_id);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/Back_end/rentOrder/listAllRentOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentOrder/addRentOrder.jsp");
				failureView.forward(req, res);
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
				String ro_id =req.getParameter("ro_id");
				
				/***************************2.�}�l�R�����***************************************/
				RentOrderService rentOrderSvc = new RentOrderService();
				rentOrderSvc.deleteRentOrder(ro_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/Back_end/rentOrder/listAllRentOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentOrder/listAllRentOrder.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
