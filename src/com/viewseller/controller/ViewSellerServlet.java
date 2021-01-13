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
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("v_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/ViewSeller/select_page.jsp");
					failureView.forward(req, res);                  
					return;// 程式中斷
				}

				String v_id = null;
				try {
					v_id = str;
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/ViewSeller/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
			    ViewsellerService viewsellerSvc = new ViewsellerService();
				ViewsellerVO viewsellerVO = viewsellerSvc.getOneViewseller(v_id);
				if (viewsellerVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/ViewSeller/listOneviewseller.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("viewsellerVO", viewsellerVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/ViewSeller/listOneviewseller.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/ViewSeller/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String v_id = new String(req.getParameter("v_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				ViewsellerService viewsellerSvc = new ViewsellerService();
				ViewsellerVO viewsellerVO = viewsellerSvc.getOneViewseller(v_id);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("viewsellerVO", viewsellerVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/ViewSeller/update_viewseller_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/ViewSeller/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String v_id = new String(req.getParameter("v_id").trim());

				
				String o_id = req.getParameter("o_id").trim();
				if (o_id == null || o_id.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				
				
				String m_buyid = req.getParameter("m_buyid").trim();
				if (m_buyid == null || m_buyid.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				String m_sellid = req.getParameter("m_sellid").trim();
				if (m_sellid == null || m_sellid.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				String v_gb = req.getParameter("v_gb").trim();
				if (v_gb == null || v_gb.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				String v_comment = req.getParameter("v_comment").trim();
				if (v_comment == null || v_comment.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				
				java.sql.Timestamp v_date = null;
				try {
					v_date = java.sql.Timestamp.valueOf(req.getParameter("v_date").trim());
				} catch (Exception e) {
					v_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
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
					req.setAttribute("viewsellerVO", viewsellerVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/ViewSeller/update_viewseller_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ViewsellerService viewsellerSvc = new ViewsellerService();
				viewsellerVO = viewsellerSvc.updateViewsellerVO(v_id, o_id, m_buyid, m_sellid, v_gb, v_comment, v_date);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("viewsellerVO", viewsellerVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/ViewSeller/listOneviewseller.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/ViewSeller/update_viewseller_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//				String v_id = new String(req.getParameter("v_id").trim());

				String o_id = req.getParameter("o_id").trim();
				if (o_id == null || o_id.trim().length() == 0) {
					errorMsgs.add("o_id請勿空白");
				}
			
				String m_buyid = req.getParameter("m_buyid").trim();
				if (m_buyid == null || m_buyid.trim().length() == 0) {
					errorMsgs.add("m_buyid請勿空白");
				}
				
				String m_sellid = req.getParameter("m_sellid").trim();
				if (m_sellid == null || m_sellid.trim().length() == 0) {
					errorMsgs.add("m_sellid請勿空白");
				}
				
				String v_gb = req.getParameter("v_gb").trim();
				if (v_gb == null || v_gb.trim().length() == 0) {
					errorMsgs.add("v_gb請勿空白");
				}
				String v_comment = req.getParameter("v_comment").trim();
				if (v_comment == null || v_comment.trim().length() == 0) {
					errorMsgs.add("v_comment請勿空白");
				}
				
				java.sql.Timestamp v_date = null;
				
				try {
					v_date = java.sql.Timestamp.valueOf(req.getParameter("v_date").trim());
				} catch (Exception e) {
					v_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
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
					System.out.println("有錯誤處理");
					req.setAttribute("viewsellerVO", viewsellerVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/listOrderdetailByOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始新增資料 ***************************************/
				ViewsellerService viewsellerSvc = new ViewsellerService();
				viewsellerVO = viewsellerSvc.addViewsellerVO(o_id, m_buyid, m_sellid, v_gb, v_comment, v_date);
				
				OrderdetailService orderdetailSvc = new OrderdetailService();//
				List<OrderdetailVO> list = orderdetailSvc.getDetailByOrder(o_id);//
				req.setAttribute("list", list);//
				
				OrderlistService orderlistSvc =new OrderlistService();
				OrderlistVO orderlistVO = new OrderlistVO();

				orderlistVO.setO_id(o_id);
				orderlistSvc.updateStatusFinish(o_id);
				
				//推播開始--------------------------
				Jedis jedis = new Jedis("localhost", 6379);
				jedis.auth("123456");
				
				ObjectMapper mapper = new ObjectMapper();
				WebSocket ws = new WebSocket();
				BellVO bellVO = new BellVO();
				
				bellVO.setM_id(m_sellid);
				bellVO.setMessage("您的訂單"+o_id+"有一則新評價");
				
				ws.onMessage(mapper.writeValueAsString(bellVO));
				
				jedis.close();
				//推播結束--------------------------
				
				
			    req.setAttribute("viewsellerVO",viewsellerVO);
			    
				String url = "/Back_end/OrderDetail/odcommentview.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/listOrderdetailByOrder.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String v_id = new String(req.getParameter("v_id"));
				/*************************** 2.開始刪除資料 ***************************************/
				ViewsellerService viewsellerSvc = new ViewsellerService();
				viewsellerSvc.deleteViewseller(v_id);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/Back_end/ViewSeller/listAllviewseller.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/ViewSeller/listAllviewseller.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
