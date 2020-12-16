package com.rent.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rent.model.RentService;
import com.rent.model.RentVO;

@WebServlet("/RentServlet")

public class RentServlet extends HttpServlet {

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
				String str = req.getParameter("r_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/Rent/index.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String  r_id = null;
				try {
					r_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/Rent/index.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				RentService rentSvc = new RentService();
				RentVO rentVO = rentSvc.getOneRent(str);
				if (rentVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/Rent/index.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("rentVO",rentVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/Rent/listOneRent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/Rent/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String r_id = req.getParameter("r_id");
				
				/***************************2.�}�l�d�߸��****************************************/
				RentService rentSvc = new RentService();
				RentVO rentVO = rentSvc.getOneRent(r_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("rentVO", rentVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/Rent/update_rent_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/Rent/update_rent_input.jsp");
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
				String r_id = req.getParameter("r_id");
				String r_type = req.getParameter("r_type");
				String r_typeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (r_type == null || r_type.trim().length() == 0) {
					errorMsgs.add("�X���~����: �ФŪť�");
				} else if(!r_type.trim().matches(r_typeReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�X���~����: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				String pt_id = req.getParameter("pt_id");
				if (pt_id == null || pt_id.trim().length() == 0) {
					errorMsgs.add("����ID�ФŪť�");
				}
				
				String r_name = req.getParameter("r_name").trim();
				if (r_name == null || r_name.trim().length() == 0) {
					errorMsgs.add("�X���~�W�ٽФŪť�");
				}
				
				String r_describe = req.getParameter("r_describe").trim();
				if (r_describe == null || r_describe.trim().length() == 0) {
					errorMsgs.add("�ӫ~�y�z�ФŪť�");
				}
				
				String r_situation = req.getParameter("r_situation").trim();
				if (r_situation == null || r_situation.trim().length() == 0) {
					errorMsgs.add("�f�p�ФŪť�");
				}
				
				String r_status = req.getParameter("r_status").trim();
				if (r_status == null || r_status.trim().length() == 0) {
					errorMsgs.add("�f�����A�ФŪť�");
				}
				
				Integer r_price = null;
				try {
					r_price = new Integer(req.getParameter("r_price").trim());
				} catch (NumberFormatException e) {
					r_price = 0;
					errorMsgs.add("����ж�Ʀr.");
				}
				
				Timestamp r_adddate=Timestamp.valueOf(req.getParameter("r_adddate").trim());
//				try {
//					r_adddate = Timestamp.valueOf(req.getParameter("r_revisedate").trim());
//				} catch (IllegalArgumentException e) {
//					r_adddate=new Timestamp(System.currentTimeMillis());
//					errorMsgs.add("�п�J�ק���!");
//				}
				
				Timestamp r_revisedate= null;
				try {
					r_revisedate = Timestamp.valueOf(req.getParameter("r_revisedate").trim());
				} catch (IllegalArgumentException e) {
					r_revisedate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�ק���!");
				}
				String e_editorid = req.getParameter("e_editorid");
				if (e_editorid == null || e_editorid.trim().length() == 0) {
					errorMsgs.add("�ק��ID�ФŪť�");
				}
				String st_id = req.getParameter("st_id");
				if (st_id == null || st_id.trim().length() == 0) {
					errorMsgs.add("����ID�ФŪť�");
				}
				String e_addid = req.getParameter("e_addid");
				if (e_addid == null || e_addid.trim().length() == 0) {
					errorMsgs.add("�s�W��ID�ФŪť�");
				}
				
				
				RentVO rentVO = new RentVO();
				rentVO.setR_id(r_id);
				rentVO.setR_type(r_type);
				rentVO.setR_name(r_name);
				rentVO.setPt_id(pt_id);
				rentVO.setR_describe(r_describe);
				rentVO.setR_situation(r_situation);
				rentVO.setR_status(r_status);
				rentVO.setR_price(r_price);
				rentVO.setR_adddate(r_adddate);
				rentVO.setR_revisedate(r_revisedate);
				rentVO.setE_editorid(e_editorid);
				rentVO.setE_addid(e_addid);
				rentVO.setSt_id(st_id);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentVO", rentVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/Rent/update_rent_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				RentService rentSvc = new RentService();
				rentVO = rentSvc.updateRent(r_type, r_name, pt_id, r_describe, r_situation, r_status, r_price, r_adddate, r_revisedate, e_addid, e_editorid, st_id, r_id);
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("rentVO", rentVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/Back_end/Rent/listOneRent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/Rent/update_rent_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String r_type = req.getParameter("r_type");
				String r_typeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (r_type == null || r_type.trim().length() == 0) {
					errorMsgs.add("�X���~����: �ФŪť�");
				} else if(!r_type.trim().matches(r_typeReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�X���~����: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				String r_name = req.getParameter("r_name").trim();
				if (r_name == null || r_name.trim().length() == 0) {
					errorMsgs.add("�X���~�W�ٽФŪť�");
				}
				
				String r_describe = req.getParameter("r_describe").trim();
				if (r_describe == null || r_describe.trim().length() == 0) {
					errorMsgs.add("�ӫ~�y�z�ФŪť�");
				}
				
				String r_situation = req.getParameter("r_situation").trim();
				if (r_situation == null || r_situation.trim().length() == 0) {
					errorMsgs.add("�f�p�ФŪť�");
				}
				
				String r_status = req.getParameter("r_status").trim();
				if (r_status == null || r_status.trim().length() == 0) {
					errorMsgs.add("�f�����A�ФŪť�");
				}
				
				Integer r_price = null;
				try {
					r_price = new Integer(req.getParameter("r_price").trim());
				} catch (NumberFormatException e) {
					r_price = 0;
					errorMsgs.add("����ж�Ʀr.");
				}
				
				Timestamp r_adddate= null;
				try {
					r_adddate = Timestamp.valueOf(req.getParameter("r_adddate").trim());
				} catch (IllegalArgumentException e) {
					r_adddate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�s�W���!");
				}
				
				Timestamp r_revisedate= null;
				try {
					r_revisedate = Timestamp.valueOf(req.getParameter("r_revisedate").trim());
				} catch (IllegalArgumentException e) {
					r_revisedate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�ק���!");
				}
				
				
				String pt_id = req.getParameter("pt_id").trim();
				if (pt_id == null || pt_id.trim().length() == 0) {
					errorMsgs.add("����ID�ФŪť�");
				}
				String e_addid = req.getParameter("e_addid").trim();
				if (e_addid == null || e_addid.trim().length() == 0) {
					errorMsgs.add("�s�W��ID�ФŪť�");
				}
				String e_editorid = req.getParameter("e_editorid").trim();
				if (e_editorid == null || e_editorid.trim().length() == 0) {
					errorMsgs.add("�ק��ID�ФŪť�");
				}
				String st_id = req.getParameter("st_id").trim();
				if (st_id == null || st_id.trim().length() == 0) {
					errorMsgs.add("����ID�ФŪť�");
				}
				RentVO rentVO = new RentVO();
				rentVO.setR_type(r_type);
				rentVO.setR_name(r_name);
				rentVO.setPt_id(pt_id);
				rentVO.setR_describe(r_describe);
				rentVO.setR_situation(r_situation);
				rentVO.setR_status(r_status);
				rentVO.setR_price(r_price);
				rentVO.setR_adddate(r_adddate);
				rentVO.setR_revisedate(r_revisedate);
				rentVO.setE_addid(e_addid);
				rentVO.setE_editorid(e_editorid);
				rentVO.setSt_id(st_id);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentVO", rentVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/Rent/addRent.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				RentService rentSvc = new RentService();
				rentVO = rentSvc.addRent(r_type, r_name, pt_id, r_describe, r_situation, r_status, r_price, r_adddate, r_revisedate, e_addid, e_editorid, st_id);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/Back_end/Rent/listAllRent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/Rent/addRent.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String r_id = req.getParameter("r_id");
				
				/***************************2.�}�l�R�����***************************************/
				RentService rentSvc = new RentService();
				rentSvc.deleteRent(r_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/Back_end/Rent/listAllRent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/Rent/listAllRent.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

