package com.store.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.store.model.*;

@WebServlet("/store/StoreServlet")
public class StoreServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Enter StoreServlet");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("st_id");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�����s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String st_id = null;
				try {
					st_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�����s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getOneStore(st_id);
				if (storeVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("storeVO", storeVO); // ��Ʈw���X��blacklistVO����,�s�Jreq
				String url = "/store/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneStore.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllStore.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String st_id = new String(req.getParameter("st_id"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getOneStore(st_id);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("storeVO", storeVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/store/update_store_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_store_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/listAllStore.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_store_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String st_id = req.getParameter("st_id");
				
				String st_name = req.getParameter("st_name");
				if (st_name == null || st_name.trim().length() == 0) {
					errorMsgs.add("�����W��: �ФŪť�");
				}
				
				String st_tel = req.getParameter("st_tel");
				String st_telReg = "0\\d{1,2}-(\\d{8})$";
				if (st_tel == null || st_tel.trim().length() == 0) {
					errorMsgs.add("�����q��: �ФŪť�");
				}else if(!st_tel.trim().matches(st_telReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�����q��: (�ϽX)-(8��Ʀr)");
	            }
				
				String st_address = req.getParameter("st_address");
				if (st_address == null || st_address.trim().length() == 0) {
					errorMsgs.add("�����a�}: �ФŪť�");
				}
				

				StoreVO storeVO = new StoreVO();
				storeVO.setSt_id(st_id);
				storeVO.setSt_name(st_name);
				storeVO.setSt_tel(st_tel);
				storeVO.setSt_address(st_address);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/store/update_store_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.updateStore(st_id, st_name, st_tel, st_address);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("storeVO", storeVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/store/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/update_store_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				String st_name = req.getParameter("st_name");
				if (st_name == null || st_name.trim().length() == 0) {
					errorMsgs.add("�����W�ٽФŪť�");
				}
				String st_tel = req.getParameter("st_tel");
				String st_telReg = "0\\d{1,2}-(\\d{8})$";
				if (st_tel == null || st_tel.trim().length() == 0) {
					errorMsgs.add("�����q��: �ФŪť�");
				}else if(!st_tel.trim().matches(st_telReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�����q��: (�ϽX)-(8��Ʀr)");
	            }
				String st_address = req.getParameter("st_address");
				if (st_address == null || st_address.trim().length() == 0) {
					errorMsgs.add("�����q�ܽФŪť�");
				}

				StoreVO storeVO = new StoreVO();
				storeVO.setSt_name(st_name);
				storeVO.setSt_tel(st_tel);
				storeVO.setSt_address(st_address);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/store/addStore.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.addStore(st_name,st_tel,st_address);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/store/listAllStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/addStore.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String st_id = new String(req.getParameter("st_id"));

				/*************************** 2.�}�l�R����� ***************************************/
				StoreService storeSvc = new StoreService();
				storeSvc.deleteStore(st_id);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/store/listAllStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/listAllStore.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
