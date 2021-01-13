package com.viewseller.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bell.model.BellVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderdetail.model.OrderdetailService;
import com.orderdetail.model.OrderdetailVO;
import com.orderlist.model.OrderlistService;
import com.orderlist.model.OrderlistVO;
import com.viewseller.model.*;
import com.websocket.WebSocket;

import redis.clients.jedis.Jedis;


@WebServlet("/ViewSellerServlet")
public class ViewSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("v_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/ViewSeller/select_page.jsp");
					failureView.forward(req, res);                  
					return;// �{�����_
				}

				String v_id = null;
				try {
					v_id = str;
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/ViewSeller/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
			    ViewsellerService viewsellerSvc = new ViewsellerService();
				ViewsellerVO viewsellerVO = viewsellerSvc.getOneViewseller(v_id);
				if (viewsellerVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/ViewSeller/listOneviewseller.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("viewsellerVO", viewsellerVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/ViewSeller/listOneviewseller.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/ViewSeller/select_page.jsp");
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
				String v_id = new String(req.getParameter("v_id"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				ViewsellerService viewsellerSvc = new ViewsellerService();
				ViewsellerVO viewsellerVO = viewsellerSvc.getOneViewseller(v_id);
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("viewsellerVO", viewsellerVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/Back_end/ViewSeller/update_viewseller_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/ViewSeller/select_page.jsp");
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

				String v_id = new String(req.getParameter("v_id").trim());

				
				String o_id = req.getParameter("o_id").trim();
				if (o_id == null || o_id.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				
				
				String m_buyid = req.getParameter("m_buyid").trim();
				if (m_buyid == null || m_buyid.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				String m_sellid = req.getParameter("m_sellid").trim();
				if (m_sellid == null || m_sellid.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				String v_gb = req.getParameter("v_gb").trim();
				if (v_gb == null || v_gb.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				String v_comment = req.getParameter("v_comment").trim();
				if (v_comment == null || v_comment.trim().length() == 0) {
					errorMsgs.add("�ФŪť�");
				}
				
				java.sql.Timestamp v_date = null;
				try {
					v_date = java.sql.Timestamp.valueOf(req.getParameter("v_date").trim());
				} catch (Exception e) {
					v_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				ViewsellerVO viewsellerVO = new ViewsellerVO();
				viewsellerVO.setV_id(v_id);
				viewsellerVO.setO_id(o_id);
				viewsellerVO.setM_buyid(m_buyid);
				viewsellerVO.setM_sellid(m_sellid);
				viewsellerVO.setV_gb(v_gb);
				viewsellerVO.setV_comment(v_comment);
				viewsellerVO.setV_date(v_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("viewsellerVO", viewsellerVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/ViewSeller/update_viewseller_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				ViewsellerService viewsellerSvc = new ViewsellerService();
				viewsellerVO = viewsellerSvc.updateViewsellerVO(v_id, o_id, m_buyid, m_sellid, v_gb, v_comment, v_date);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("viewsellerVO", viewsellerVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/Back_end/ViewSeller/listOneviewseller.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/ViewSeller/update_viewseller_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
//				String v_id = new String(req.getParameter("v_id").trim());

				String o_id = req.getParameter("o_id").trim();
				if (o_id == null || o_id.trim().length() == 0) {
					errorMsgs.add("o_id�ФŪť�");
				}
			
				String m_buyid = req.getParameter("m_buyid").trim();
				if (m_buyid == null || m_buyid.trim().length() == 0) {
					errorMsgs.add("m_buyid�ФŪť�");
				}
				
				String m_sellid = req.getParameter("m_sellid").trim();
				if (m_sellid == null || m_sellid.trim().length() == 0) {
					errorMsgs.add("m_sellid�ФŪť�");
				}
				
				String v_gb = req.getParameter("v_gb").trim();
				if (v_gb == null || v_gb.trim().length() == 0) {
					errorMsgs.add("v_gb�ФŪť�");
				}
				String v_comment = req.getParameter("v_comment").trim();
				if (v_comment == null || v_comment.trim().length() == 0) {
					errorMsgs.add("v_comment�ФŪť�");
				}
				
				java.sql.Timestamp v_date = null;
				
				try {
					v_date = java.sql.Timestamp.valueOf(req.getParameter("v_date").trim());
				} catch (Exception e) {
					v_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
//				Date d = new Date();
//				Timestamp addDate = new Timestamp(d.getTime());
//				HttpSession session = req.getSession();
//				String mid = (String) session.getAttribute("loginId");
//				System.out.println(mid);
				
				ViewsellerVO viewsellerVO = new ViewsellerVO();
//				viewsellerVO.setV_id(v_id);
				viewsellerVO.setO_id(o_id);
				viewsellerVO.setM_buyid(m_buyid);
				viewsellerVO.setM_sellid(m_sellid);
				viewsellerVO.setV_gb(v_gb);
				viewsellerVO.setV_comment(v_comment);
				viewsellerVO.setV_date(v_date);
				
				if (!errorMsgs.isEmpty()) {
					System.out.println("�����~�B�z");
					req.setAttribute("viewsellerVO", viewsellerVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/listOrderdetailByOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.�}�l�s�W��� ***************************************/
				ViewsellerService viewsellerSvc = new ViewsellerService();
				viewsellerVO = viewsellerSvc.addViewsellerVO(o_id, m_buyid, m_sellid, v_gb, v_comment, v_date);
				
				OrderdetailService orderdetailSvc = new OrderdetailService();//
				List<OrderdetailVO> list = orderdetailSvc.getDetailByOrder(o_id);//
				req.setAttribute("list", list);//
				
				OrderlistService orderlistSvc =new OrderlistService();
				OrderlistVO orderlistVO = new OrderlistVO();

				orderlistVO.setO_id(o_id);
				orderlistSvc.updateStatusFinish(o_id);
				
				//�����}�l--------------------------
				Jedis jedis = new Jedis("localhost", 6379);
				jedis.auth("123456");
				
				ObjectMapper mapper = new ObjectMapper();
				WebSocket ws = new WebSocket();
				BellVO bellVO = new BellVO();
				
				bellVO.setM_id(m_sellid);
				bellVO.setMessage("�z���q��"+o_id+"���@�h�s����");
				
				ws.onMessage(mapper.writeValueAsString(bellVO));
				
				jedis.close();
				//��������--------------------------
				
				
			    req.setAttribute("viewsellerVO",viewsellerVO);
			    
				String url = "/Back_end/OrderDetail/odcommentview.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/listOrderdetailByOrder.jsp");
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
				String v_id = new String(req.getParameter("v_id"));
				/*************************** 2.�}�l�R����� ***************************************/
				ViewsellerService viewsellerSvc = new ViewsellerService();
				viewsellerSvc.deleteViewseller(v_id);
				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/Back_end/ViewSeller/listAllviewseller.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/ViewSeller/listAllviewseller.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
