package com.rent.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rent.model.RentService;
import com.rent.model.RentVO;

@WebServlet("/RentServlet")

public class RentServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("r_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/Rent/index.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String  r_id = null;
				try {
					r_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/Rent/index.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RentService rentSvc = new RentService();
				RentVO rentVO = rentSvc.getOneRent(str);
				if (rentVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/Rent/index.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rentVO",rentVO); // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/Rent/listOneRent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/Rent/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String r_id = req.getParameter("r_id");
				
				/***************************2.開始查詢資料****************************************/
				RentService rentSvc = new RentService();
				RentVO rentVO = rentSvc.getOneRent(r_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("rentVO", rentVO);         // 資料庫取出的empVO物件,存入req
				String url = "/Back_end/Rent/update_rent_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/Rent/update_rent_input.jsp");
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
				String r_id = req.getParameter("r_id");
				String r_type = req.getParameter("r_type");
				String r_typeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (r_type == null || r_type.trim().length() == 0) {
					errorMsgs.add("出租品種類: 請勿空白");
				} else if(!r_type.trim().matches(r_typeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("出租品種類: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String pt_id = req.getParameter("pt_id");
				if (pt_id == null || pt_id.trim().length() == 0) {
					errorMsgs.add("種類ID請勿空白");
				}
				
				String r_name = req.getParameter("r_name").trim();
				if (r_name == null || r_name.trim().length() == 0) {
					errorMsgs.add("出租品名稱請勿空白");
				}
				
				String r_describe = req.getParameter("r_describe").trim();
				if (r_describe == null || r_describe.trim().length() == 0) {
					errorMsgs.add("商品描述請勿空白");
				}
				
				String r_situation = req.getParameter("r_situation").trim();
				if (r_situation == null || r_situation.trim().length() == 0) {
					errorMsgs.add("貨況請勿空白");
				}
				
				String r_status = req.getParameter("r_status").trim();
				if (r_status == null || r_status.trim().length() == 0) {
					errorMsgs.add("貨物狀態請勿空白");
				}
				
				Integer r_price = null;
				try {
					r_price = new Integer(req.getParameter("r_price").trim());
				} catch (NumberFormatException e) {
					r_price = 0;
					errorMsgs.add("價格請填數字.");
				}
				
				Timestamp r_adddate=Timestamp.valueOf(req.getParameter("r_adddate").trim());
//				try {
//					r_adddate = Timestamp.valueOf(req.getParameter("r_revisedate").trim());
//				} catch (IllegalArgumentException e) {
//					r_adddate=new Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入修改日期!");
//				}
				
				Timestamp r_revisedate= null;
				try {
					r_revisedate = Timestamp.valueOf(req.getParameter("r_revisedate").trim());
				} catch (IllegalArgumentException e) {
					r_revisedate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入修改日期!");
				}
				String e_editorid = req.getParameter("e_editorid");
				if (e_editorid == null || e_editorid.trim().length() == 0) {
					errorMsgs.add("修改者ID請勿空白");
				}
				String st_id = req.getParameter("st_id");
				if (st_id == null || st_id.trim().length() == 0) {
					errorMsgs.add("門市ID請勿空白");
				}
				String e_addid = req.getParameter("e_addid");
				if (e_addid == null || e_addid.trim().length() == 0) {
					errorMsgs.add("新增者ID請勿空白");
				}
				
				
				RentVO rentVO = new RentVO();
				rentVO.setR_id(r_id);
				rentVO.setR_type(r_type);
				rentVO.setR_name(r_name);
				rentVO.setPt_id(pt_id);
				rentVO.setR_describe(r_describe);
				rentVO.setR_situation(r_situation);
				rentVO.setR_status(r_status);
				rentVO.setR_price(r_price);
				rentVO.setR_adddate(r_adddate);
				rentVO.setR_revisedate(r_revisedate);
				rentVO.setE_editorid(e_editorid);
				rentVO.setE_addid(e_addid);
				rentVO.setSt_id(st_id);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentVO", rentVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/Rent/update_rent_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RentService rentSvc = new RentService();
				rentVO = rentSvc.updateRent(r_type, r_name, pt_id, r_describe, r_situation, r_status, r_price, r_adddate, r_revisedate, e_addid, e_editorid, st_id, r_id);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("rentVO", rentVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/Back_end/Rent/listOneRent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/Rent/update_rent_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String r_type = req.getParameter("r_type");
				String r_typeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (r_type == null || r_type.trim().length() == 0) {
					errorMsgs.add("出租品種類: 請勿空白");
				} else if(!r_type.trim().matches(r_typeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("出租品種類: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String r_name = req.getParameter("r_name").trim();
				if (r_name == null || r_name.trim().length() == 0) {
					errorMsgs.add("出租品名稱請勿空白");
				}
				
				String r_describe = req.getParameter("r_describe").trim();
				if (r_describe == null || r_describe.trim().length() == 0) {
					errorMsgs.add("商品描述請勿空白");
				}
				
				String r_situation = req.getParameter("r_situation").trim();
				if (r_situation == null || r_situation.trim().length() == 0) {
					errorMsgs.add("貨況請勿空白");
				}
				
				String r_status = req.getParameter("r_status").trim();
				if (r_status == null || r_status.trim().length() == 0) {
					errorMsgs.add("貨物狀態請勿空白");
				}
				
				Integer r_price = null;
				try {
					r_price = new Integer(req.getParameter("r_price").trim());
				} catch (NumberFormatException e) {
					r_price = 0;
					errorMsgs.add("價格請填數字.");
				}
				
				Timestamp r_adddate= null;
				try {
					r_adddate = Timestamp.valueOf(req.getParameter("r_adddate").trim());
				} catch (IllegalArgumentException e) {
					r_adddate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入新增日期!");
				}
				
				Timestamp r_revisedate= null;
				try {
					r_revisedate = Timestamp.valueOf(req.getParameter("r_revisedate").trim());
				} catch (IllegalArgumentException e) {
					r_revisedate=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入修改日期!");
				}
				
				
				String pt_id = req.getParameter("pt_id").trim();
				if (pt_id == null || pt_id.trim().length() == 0) {
					errorMsgs.add("種類ID請勿空白");
				}
				String e_addid = req.getParameter("e_addid").trim();
				if (e_addid == null || e_addid.trim().length() == 0) {
					errorMsgs.add("新增者ID請勿空白");
				}
				String e_editorid = req.getParameter("e_editorid").trim();
				if (e_editorid == null || e_editorid.trim().length() == 0) {
					errorMsgs.add("修改者ID請勿空白");
				}
				String st_id = req.getParameter("st_id").trim();
				if (st_id == null || st_id.trim().length() == 0) {
					errorMsgs.add("門市ID請勿空白");
				}
				RentVO rentVO = new RentVO();
				rentVO.setR_type(r_type);
				rentVO.setR_name(r_name);
				rentVO.setPt_id(pt_id);
				rentVO.setR_describe(r_describe);
				rentVO.setR_situation(r_situation);
				rentVO.setR_status(r_status);
				rentVO.setR_price(r_price);
				rentVO.setR_adddate(r_adddate);
				rentVO.setR_revisedate(r_revisedate);
				rentVO.setE_addid(e_addid);
				rentVO.setE_editorid(e_editorid);
				rentVO.setSt_id(st_id);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rentVO", rentVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/Rent/addRent.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RentService rentSvc = new RentService();
				rentVO = rentSvc.addRent(r_type, r_name, pt_id, r_describe, r_situation, r_status, r_price, r_adddate, r_revisedate, e_addid, e_editorid, st_id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/Back_end/Rent/listAllRent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/Rent/addRent.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String r_id = req.getParameter("r_id");
				
				/***************************2.開始刪除資料***************************************/
				RentService rentSvc = new RentService();
				rentSvc.deleteRent(r_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/Back_end/Rent/listAllRent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/Rent/listAllRent.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

