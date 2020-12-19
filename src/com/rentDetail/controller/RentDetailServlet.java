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
		
//顯示單筆
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("rd_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentDetail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String rd_id = null;
				try {
					rd_id = str;
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentDetail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RentDetailService rentDetailSvc = new RentDetailService();
				RentDetailVO rentDetailVO = rentDetailSvc.getOneRentDetail(rd_id);
				if (rentDetailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentDetail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rentDetailVO", rentDetailVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/rentDetail/listOneRentDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentDetail/select_page.jsp");
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
				String rd_id = req.getParameter("rd_id");
				
				/***************************2.開始查詢資料****************************************/
				RentDetailService rentDetailSvc = new RentDetailService();
				RentDetailVO rentDetailVO = rentDetailSvc.getOneRentDetail(rd_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("rentDetailVO", rentDetailVO);         // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/rentDetail/update_rentDetail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentDetail/listAllRentDetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String rd_id = req.getParameter("rd_id").trim();
				
				
				String ro_id = req.getParameter("ro_id").trim();

				String r_id = req.getParameter("r_id").trim();

				Integer rd_count = null;
				try {
					rd_count = new Integer(req.getParameter("rd_count").trim());
				} catch (NumberFormatException e) {
					rd_count = 0;
					errorMsgs.add("獎金請填數字.");
				}

				RentDetailVO rentDetailVO = new RentDetailVO();
				rentDetailVO.setRd_id(rd_id);
				rentDetailVO.setRo_id(ro_id);
				rentDetailVO.setR_id(r_id);
				rentDetailVO.setRd_count(rd_count);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentDetailVO", rentDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentDetail/update_rentDetail_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RentDetailService rentDetailSvc = new RentDetailService();
				rentDetailVO = rentDetailSvc.updateRentDetail(rd_id, ro_id, r_id, rd_count);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rentDetailVO", rentDetailVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/rentDetail/listOneRentDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentDetail/update_rentDetail_input.jsp");
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
				String ro_id = req.getParameter("ro_id").trim();

				String r_id = req.getParameter("r_id").trim();

				Integer rd_count = null;
				try {
					rd_count = new Integer(req.getParameter("rd_count").trim());
				} catch (NumberFormatException e) {
					rd_count = 0;
					errorMsgs.add("獎金請填數字.");
				}

				RentDetailVO rentDetailVO = new RentDetailVO();
				rentDetailVO.setRo_id(ro_id);
				rentDetailVO.setR_id(r_id);
				rentDetailVO.setRd_count(rd_count);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentDetailVO", rentDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/rentDetail/addRentDetail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				System.out.println("1");
				RentDetailService rentDetailSvc = new RentDetailService();
				System.out.println("2");
				rentDetailVO = rentDetailSvc.addRentDetail(ro_id, r_id, rd_count);
				System.out.println("3");
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				System.out.println("4");
				String url = "/Back_end/rentDetail/listAllRentDetail.jsp";
				System.out.println("5");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				System.out.println("6");
				successView.forward(req, res);				
				System.out.println("7");
				
				/***************************其他可能的錯誤處理**********************************/
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
		
//刪除		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String rd_id =req.getParameter("rd_id");
				
				/***************************2.開始刪除資料***************************************/
				RentDetailService rentDetailSvc = new RentDetailService();
				rentDetailSvc.deleteRentDetail(rd_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/Back_end/rentDetail/listAllRentDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/rentDetail/listAllRentDetail.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
