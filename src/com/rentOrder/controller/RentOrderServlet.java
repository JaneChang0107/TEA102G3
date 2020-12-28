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
		
//顯示單筆		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ro_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentOrder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String ro_id = null;
				try {
					ro_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentOrder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RentOrderService rentOrderSvc = new RentOrderService();
				RentOrderVO rentOrderVO = rentOrderSvc.getOneRentOrder(ro_id);
				if (rentOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentOrder/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rentOrderVO", rentOrderVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/rentOrder/listOneRentOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentOrder/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
//更新單筆
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String ro_id = req.getParameter("ro_id");
				
				/***************************2.開始查詢資料****************************************/
				RentOrderService rentOrderSvc = new RentOrderService();
				RentOrderVO rentOrderVO = rentOrderSvc.getOneRentOrder(ro_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("rentOrderVO", rentOrderVO);         // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/rentOrder/update_rentOrder_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentOrder/listAllRentOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
//更新
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String ro_id = req.getParameter("ro_id").trim();	
				
				java.sql.Timestamp ro_date = null;
				try {
					ro_date = Timestamp.valueOf(req.getParameter("ro_date").trim());
				} catch (IllegalArgumentException e) {
					ro_date=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String ro_status = req.getParameter("ro_status").trim();
				if (ro_status == null || ro_status.trim().length() == 0) {
					errorMsgs.add("員工狀態請勿空白");
				}	
				
				
				String st_id = req.getParameter("st_id").trim();
				if (st_id == null || st_id.trim().length() == 0) {
					errorMsgs.add("門市編號請勿空白");
				}	
				
				java.sql.Timestamp ro_outdate = null;
				try {
					ro_outdate = Timestamp.valueOf(req.getParameter("ro_outdate").trim());
				} catch (IllegalArgumentException e) {
					ro_outdate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入出租日期!");
				}
				
				java.sql.Timestamp ro_backdate = null;
				try {
					ro_backdate = Timestamp.valueOf(req.getParameter("ro_backdate").trim());
				} catch (IllegalArgumentException e) {
					ro_backdate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入歸還日期!");
				}

				Integer ro_deposit = null;
				try {
					ro_deposit = new Integer(req.getParameter("ro_deposit").trim());
				} catch (NumberFormatException e) {
					ro_deposit = 0;
					errorMsgs.add("租借總金額請填數字.");
				}
				
				Integer ro_total = null;
				try {
					ro_total = new Integer(req.getParameter("ro_total").trim());
				} catch (NumberFormatException e) {
					ro_total = 0;
					errorMsgs.add("租借總金額請填數字.");
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
					errorMsgs.add("坤幣請填數字.");
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
					req.setAttribute("rentOrderVO", rentOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentOrder/update_rentOrder_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				rentOrderVO = rentOrderSvc.updateRentOrder(ro_id, ro_date, ro_status, st_id, ro_outdate,ro_backdate, ro_deposit, ro_total, data, ro_pm, m_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rentOrderVO", rentOrderVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/rentOrder/listOneRentOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentOrder/update_rentOrder_input.jsp");
				failureView.forward(req, res);
			}
		}
//新增
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				java.sql.Timestamp ro_date = null;
				try {
					ro_date = Timestamp.valueOf(req.getParameter("ro_date").trim());
				} catch (IllegalArgumentException e) {
					ro_date=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				System.out.println("ro_date="+ro_date);
				
				String ro_status = req.getParameter("ro_status").trim();
//				String ro_status = req.getParameter("ro_status");
//				String ro_statusReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ro_status == null || ro_status.trim().length() == 0) {
//					errorMsgs.add("員工狀態: 請勿空白");
//				} else if(!ro_status.trim().matches(ro_statusReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工狀態: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				
				String st_id = req.getParameter("st_id").trim();
				if (st_id == null || st_id.trim().length() == 0) {
					errorMsgs.add("門市編號請勿空白");
				}	
				
				java.sql.Timestamp ro_outdate = null;
				try {
					ro_outdate = Timestamp.valueOf(req.getParameter("ro_outdate").trim());
				} catch (IllegalArgumentException e) {
					ro_outdate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入出租日期!");
				}
				System.out.println("ro_outdate="+ro_outdate);
				
				java.sql.Timestamp ro_backdate = null;
				try {
					ro_backdate = Timestamp.valueOf(req.getParameter("ro_backdate").trim());
				} catch (IllegalArgumentException e) {
					ro_backdate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入歸還日期!");
				}
				System.out.println("ro_backdate="+ro_backdate);

				Integer ro_deposit = null;
				try {
					ro_deposit = new Integer(req.getParameter("ro_deposit").trim());
				} catch (NumberFormatException e) {
					ro_deposit = 0;
					errorMsgs.add("租借總金額請填數字.");
				}
				
				Integer ro_total = null;
				try {
					ro_total = new Integer(req.getParameter("ro_total").trim());
				} catch (NumberFormatException e) {
					ro_total = 0;
					errorMsgs.add("租借總金額請填數字.");
				}

				Part ro_sign = req.getPart("ro_sign");
				if (ro_sign == null || ro_sign.getSize() == 0) {
					errorMsgs.add("請上傳圖片");
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
					errorMsgs.add("坤幣請填數字.");
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
					req.setAttribute("rentOrderVO", rentOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentOrder/addRentOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RentOrderService rentOrderSvc = new RentOrderService();
				rentOrderVO = rentOrderSvc.addRentOrder(ro_date, ro_status, st_id, ro_outdate,ro_backdate, ro_deposit, ro_total, data, ro_pm, m_id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Back_end/rentOrder/listAllRentOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentOrder/addRentOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
//刪除
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String ro_id =req.getParameter("ro_id");
				
				/***************************2.開始刪除資料***************************************/
				RentOrderService rentOrderSvc = new RentOrderService();
				rentOrderSvc.deleteRentOrder(ro_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/Back_end/rentOrder/listAllRentOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentOrder/listAllRentOrder.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
