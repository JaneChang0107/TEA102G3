package com.blacklist.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.blacklist.model.*;

@WebServlet("/blacklist/BlacklistServlet")
public class BlacklistServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Enter BlacklistServlet");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("bl_id");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�¦W��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blacklist/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String bl_id = null;
				try {
					bl_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�¦W��s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blacklist/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				BlacklistService blacklistSvc = new BlacklistService();
				BlacklistVO blacklistVO = blacklistSvc.getOneBlacklist(bl_id);
				if (blacklistVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("blacklistVO", blacklistVO); // ��Ʈw���X��blacklistVO����,�s�Jreq
				String url = "/blacklist/listOneBlacklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneBlacklist.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllBlacklist.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String bl_id = new String(req.getParameter("bl_id"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				BlacklistService blacklistSvc = new BlacklistService();
				BlacklistVO blacklistVO = blacklistSvc.getOneBlacklist(bl_id);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("blacklistVO", blacklistVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/blacklist/update_blacklist_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/listAllBlacklist.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_blacklist_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String bl_id = req.getParameter("bl_id");

				String m_id = req.getParameter("m_id");

				String m_blackId = req.getParameter("m_blackId");
				if (m_id == null || m_id.trim().length() == 0) {
					errorMsgs.add("�|��id: �ФŪť�");
				}

				BlacklistVO blacklistVO = new BlacklistVO();

				blacklistVO.setBl_id(bl_id);
				blacklistVO.setM_id(m_id);
				blacklistVO.setM_blackId(m_blackId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blacklistVO", blacklistVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/update_blacklist_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				BlacklistService blacklistSvc = new BlacklistService();
				blacklistVO = blacklistSvc.updateBlacklist(bl_id, m_id, m_blackId);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("blacklistVO", blacklistVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/blacklist/listOneBlacklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/update_blacklist_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				String m_id = req.getParameter("m_id");

				if (m_id == null || m_id.trim().length() == 0) {
					errorMsgs.add("�|���s���ФŪť�");
				}
				String m_blackId = req.getParameter("m_blackId");
				if (m_blackId == null || m_blackId.trim().length() == 0) {
					errorMsgs.add("�|���s���ФŪť�");
				}

				BlacklistVO blacklistVO = new BlacklistVO();
				blacklistVO.setM_id(m_id);
				blacklistVO.setM_blackId(m_blackId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blacklistVO", blacklistVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/addBlacklist.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				BlacklistService blacklistSvc = new BlacklistService();
				blacklistVO = blacklistSvc.addBlacklist(m_id, m_blackId);

//				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/blacklist/listAllBlacklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/addBlacklist.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String bl_id = new String(req.getParameter("bl_id"));

				/*************************** 2.�}�l�R����� ***************************************/
				BlacklistService blacklistSvc = new BlacklistService();
				blacklistSvc.deleteBlacklist(bl_id);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/blacklist/listAllBlacklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/listAllBlacklist.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
