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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("bl_id");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入黑名單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blacklist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String bl_id = null;
				try {
					bl_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("黑名單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blacklist/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				BlacklistService blacklistSvc = new BlacklistService();
				BlacklistVO blacklistVO = blacklistSvc.getOneBlacklist(bl_id);
				if (blacklistVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("blacklistVO", blacklistVO); // 資料庫取出的blacklistVO物件,存入req
				String url = "/blacklist/listOneBlacklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBlacklist.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllBlacklist.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String bl_id = new String(req.getParameter("bl_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				BlacklistService blacklistSvc = new BlacklistService();
				BlacklistVO blacklistVO = blacklistSvc.getOneBlacklist(bl_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("blacklistVO", blacklistVO); // 資料庫取出的empVO物件,存入req
				String url = "/blacklist/update_blacklist_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/listAllBlacklist.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_blacklist_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String bl_id = req.getParameter("bl_id");

				String m_id = req.getParameter("m_id");

				String m_blackId = req.getParameter("m_blackId");
				if (m_id == null || m_id.trim().length() == 0) {
					errorMsgs.add("會員id: 請勿空白");
				}

				BlacklistVO blacklistVO = new BlacklistVO();

				blacklistVO.setBl_id(bl_id);
				blacklistVO.setM_id(m_id);
				blacklistVO.setM_blackId(m_blackId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blacklistVO", blacklistVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/update_blacklist_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				BlacklistService blacklistSvc = new BlacklistService();
				blacklistVO = blacklistSvc.updateBlacklist(bl_id, m_id, m_blackId);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("blacklistVO", blacklistVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/blacklist/listOneBlacklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/update_blacklist_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String m_id = req.getParameter("m_id");

				if (m_id == null || m_id.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				String m_blackId = req.getParameter("m_blackId");
				if (m_blackId == null || m_blackId.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}

				BlacklistVO blacklistVO = new BlacklistVO();
				blacklistVO.setM_id(m_id);
				blacklistVO.setM_blackId(m_blackId);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blacklistVO", blacklistVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/addBlacklist.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				BlacklistService blacklistSvc = new BlacklistService();
				blacklistVO = blacklistSvc.addBlacklist(m_id, m_blackId);

//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/blacklist/listAllBlacklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/addBlacklist.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String bl_id = new String(req.getParameter("bl_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				BlacklistService blacklistSvc = new BlacklistService();
				blacklistSvc.deleteBlacklist(bl_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/blacklist/listAllBlacklist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/blacklist/listAllBlacklist.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
