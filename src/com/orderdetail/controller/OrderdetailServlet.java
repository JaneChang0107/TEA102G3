package com.orderdetail.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderdetail.model.*;

//@WebServlet( name="test",
//urlPatterns= {"/OrderdetailServlet" },
//loadOnStartup = 1
//
//)






@WebServlet("/OrderdetailServlet")
public class OrderdetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//�d�q��Ա�
		if ("getOrderDetailByOrder".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("o_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				String o_id = null;
				try {
					o_id = str;
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				/*************************** 2.�}�l�d�߸�� *****************************************/
				OrderdetailService orderdetailSvc = new OrderdetailService();
				List<OrderdetailVO> list = orderdetailSvc.getDetailByOrder(o_id);
				req.setAttribute("list", list);

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/OrderDetail/listOneorderdetail.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				String url = "/Back_end/OrderDetail/listOrderdetailByOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("od_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				String od_id = null;
				try {
					od_id = str;
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				/*************************** 2.�}�l�d�߸�� *****************************************/
				OrderdetailService orderdetailSvc = new OrderdetailService();
				OrderdetailVO orderdetailVO = orderdetailSvc.getOneOrderdetail(od_id);
				if (orderdetailVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/OrderDetail/listOneorderdetail.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("orderdetailVO", orderdetailVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/OrderDetail/listOneorderdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String od_id = new String(req.getParameter("od_id"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				OrderdetailService orderdetailSvc = new OrderdetailService();
				OrderdetailVO orderdetailVO = orderdetailSvc.getOneOrderdetail(od_id);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("orderdetailVO", orderdetailVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/OrderDetail/update_orderdetail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				String od_id = new String(req.getParameter("od_id").trim());

				String o_id = req.getParameter("o_id").trim();
				if (o_id == null || o_id.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}

				String p_id = req.getParameter("p_id").trim();
				if (p_id == null || p_id.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				Integer od_count = null;
				try {
					od_count = new Integer(req.getParameter("od_count").trim());
				} catch (NumberFormatException e) {
					od_count = 0;
					errorMsgs.add("�ж�Ʀr.");
				}

				OrderdetailVO orderdetailVO = new OrderdetailVO();
				orderdetailVO.setOd_id(od_id);
				orderdetailVO.setO_id(o_id);
				orderdetailVO.setP_id(p_id);
				orderdetailVO.setOd_count(od_count);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderdetailVO", orderdetailVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/OrderDetail/update_orderdetail_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				OrderdetailService orderdetailSvc = new OrderdetailService();
				orderdetailVO = orderdetailSvc.updateOrderdetail(od_id, o_id, p_id, od_count);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("orderdetailVO", orderdetailVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/Back_end/OrderDetail/listOneorderdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/OrderDetail/update_orderdetail_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				String od_id = new String(req.getParameter("od_id").trim());

				String o_id = req.getParameter("o_id").trim();
				if (o_id == null || o_id.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}

				String p_id = req.getParameter("p_id").trim();
				if (p_id == null || p_id.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				Integer od_count = null;
				try {
					od_count = new Integer(req.getParameter("od_count").trim());
				} catch (NumberFormatException e) {
					od_count = 0;
					errorMsgs.add("�ж�Ʀr.");
				}
				OrderdetailVO orderdetailVO = new OrderdetailVO();
				orderdetailVO.setOd_id(od_id);
				orderdetailVO.setO_id(o_id);
				orderdetailVO.setP_id(p_id);
				orderdetailVO.setOd_count(od_count);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderdetailVO", orderdetailVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/OrderDetail/addorderdetail.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				OrderdetailService orderdetailSvc = new OrderdetailService();
				orderdetailVO = orderdetailSvc.addOrderdetail(o_id, p_id, od_count);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/Back_end/OrderDetail/listAllOrderdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/addorderdetail.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String od_id = new String(req.getParameter("od_id"));

				/*************************** 2.�}�l�R����� ***************************************/
				OrderdetailService orderdetailSvc = new OrderdetailService();
				orderdetailSvc.deleteOrderdetail(od_id);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/Back_end/OrderDetail/listAllOrderdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/OrderDetail/listAllOrderdetail.jsp");
				failureView.forward(req, res);
			}
			
			
			

		}
		
		
	}


//
//public void init() throws ServletException {
//	
//	OrderdetailService orderdetailSvc = new OrderdetailService();
//	List<OrderdetailVO> orderdetailVO =  orderdetailSvc.count();
//	
//	orderdetailVO.forEach((VO) -> {
//		System.out.println( VO.getP_id());
//		System.out.println( VO.getOd_count());
//		System.out.println("---------------------");
//			
//	});
//}


}
