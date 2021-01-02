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
		
		
		//查訂單詳情
		if ("getOrderDetailByOrder".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("o_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				String o_id = null;
				try {
					o_id = str;
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				OrderdetailService orderdetailSvc = new OrderdetailService();
				List<OrderdetailVO> list = orderdetailSvc.getDetailByOrder(o_id);
				req.setAttribute("list", list);

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/OrderDetail/listOneorderdetail.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				String url = "/Back_end/OrderDetail/listOrderdetailByOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("od_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				String od_id = null;
				try {
					od_id = str;
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				OrderdetailService orderdetailSvc = new OrderdetailService();
				OrderdetailVO orderdetailVO = orderdetailSvc.getOneOrderdetail(od_id);
				if (orderdetailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/OrderDetail/listOneorderdetail.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("orderdetailVO", orderdetailVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/OrderDetail/listOneorderdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
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
				String od_id = new String(req.getParameter("od_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				OrderdetailService orderdetailSvc = new OrderdetailService();
				OrderdetailVO orderdetailVO = orderdetailSvc.getOneOrderdetail(od_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("orderdetailVO", orderdetailVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/OrderDetail/update_orderdetail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/select_page.jsp");
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

				String od_id = new String(req.getParameter("od_id").trim());

				String o_id = req.getParameter("o_id").trim();
				if (o_id == null || o_id.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}

				String p_id = req.getParameter("p_id").trim();
				if (p_id == null || p_id.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				Integer od_count = null;
				try {
					od_count = new Integer(req.getParameter("od_count").trim());
				} catch (NumberFormatException e) {
					od_count = 0;
					errorMsgs.add("請填數字.");
				}

				OrderdetailVO orderdetailVO = new OrderdetailVO();
				orderdetailVO.setOd_id(od_id);
				orderdetailVO.setO_id(o_id);
				orderdetailVO.setP_id(p_id);
				orderdetailVO.setOd_count(od_count);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderdetailVO", orderdetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/OrderDetail/update_orderdetail_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				OrderdetailService orderdetailSvc = new OrderdetailService();
				orderdetailVO = orderdetailSvc.updateOrderdetail(od_id, o_id, p_id, od_count);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("orderdetailVO", orderdetailVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/OrderDetail/listOneorderdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/OrderDetail/update_orderdetail_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String od_id = new String(req.getParameter("od_id").trim());

				String o_id = req.getParameter("o_id").trim();
				if (o_id == null || o_id.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}

				String p_id = req.getParameter("p_id").trim();
				if (p_id == null || p_id.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}
				Integer od_count = null;
				try {
					od_count = new Integer(req.getParameter("od_count").trim());
				} catch (NumberFormatException e) {
					od_count = 0;
					errorMsgs.add("請填數字.");
				}
				OrderdetailVO orderdetailVO = new OrderdetailVO();
				orderdetailVO.setOd_id(od_id);
				orderdetailVO.setO_id(o_id);
				orderdetailVO.setP_id(p_id);
				orderdetailVO.setOd_count(od_count);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("orderdetailVO", orderdetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/OrderDetail/addorderdetail.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				OrderdetailService orderdetailSvc = new OrderdetailService();
				orderdetailVO = orderdetailSvc.addOrderdetail(o_id, p_id, od_count);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/Back_end/OrderDetail/listAllOrderdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/Back_end/OrderDetail/addorderdetail.jsp");
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
				String od_id = new String(req.getParameter("od_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				OrderdetailService orderdetailSvc = new OrderdetailService();
				orderdetailSvc.deleteOrderdetail(od_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/Back_end/OrderDetail/listAllOrderdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
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
