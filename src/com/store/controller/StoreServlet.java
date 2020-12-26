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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("st_id");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入門市編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String st_id = null;
				try {
					st_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("門市編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getOneStore(st_id);
				if (storeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("storeVO", storeVO); // 資料庫取出的blacklistVO物件,存入req
				String url = "/store/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneStore.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllStore.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String st_id = new String(req.getParameter("st_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getOneStore(st_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("storeVO", storeVO); // 資料庫取出的empVO物件,存入req
				String url = "/store/update_store_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_store_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/listAllStore.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_store_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String st_id = req.getParameter("st_id");
				
				String st_name = req.getParameter("st_name");
				if (st_name == null || st_name.trim().length() == 0) {
					errorMsgs.add("門市名稱: 請勿空白");
				}
				
				String st_tel = req.getParameter("st_tel");
				String st_telReg = "0\\d{1,2}-(\\d{8})$";
				if (st_tel == null || st_tel.trim().length() == 0) {
					errorMsgs.add("門市電話: 請勿空白");
				}else if(!st_tel.trim().matches(st_telReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("門市電話: (區碼)-(8位數字)");
	            }
				
				String st_address = req.getParameter("st_address");
				if (st_address == null || st_address.trim().length() == 0) {
					errorMsgs.add("門市地址: 請勿空白");
				}
				

				StoreVO storeVO = new StoreVO();
				storeVO.setSt_id(st_id);
				storeVO.setSt_name(st_name);
				storeVO.setSt_tel(st_tel);
				storeVO.setSt_address(st_address);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/store/update_store_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.updateStore(st_id, st_name, st_tel, st_address);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("storeVO", storeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/store/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/update_store_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String st_name = req.getParameter("st_name");
				if (st_name == null || st_name.trim().length() == 0) {
					errorMsgs.add("門市名稱請勿空白");
				}
				String st_tel = req.getParameter("st_tel");
				String st_telReg = "0\\d{1,2}-(\\d{8})$";
				if (st_tel == null || st_tel.trim().length() == 0) {
					errorMsgs.add("門市電話: 請勿空白");
				}else if(!st_tel.trim().matches(st_telReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("門市電話: (區碼)-(8位數字)");
	            }
				String st_address = req.getParameter("st_address");
				if (st_address == null || st_address.trim().length() == 0) {
					errorMsgs.add("門市電話請勿空白");
				}

				StoreVO storeVO = new StoreVO();
				storeVO.setSt_name(st_name);
				storeVO.setSt_tel(st_tel);
				storeVO.setSt_address(st_address);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/store/addStore.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.addStore(st_name,st_tel,st_address);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/store/listAllStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/addStore.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String st_id = new String(req.getParameter("st_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				StoreService storeSvc = new StoreService();
				storeSvc.deleteStore(st_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/store/listAllStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/listAllStore.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
