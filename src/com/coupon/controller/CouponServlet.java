package com.coupon.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.coupon.model.*;

@WebServlet("/coupon/CouponServlet")
public class CouponServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Enter CouponServlet");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("co_id");

				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入優惠券編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String co_id = null;
				try {
					co_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("優惠券編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CouponService couponSvc = new CouponService();
				CouponVO couponVO = couponSvc.getOneCoupon(co_id);
				if (couponVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("couponVO", couponVO); // 資料庫取出的couponVO物件,存入req
				String url = "/coupon/listOneCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCoupon.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllCoupon.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String co_id = new String(req.getParameter("co_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				CouponService couponSvc = new CouponService();
				CouponVO couponVO = couponSvc.getOneCoupon(co_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("couponVO", couponVO); // 資料庫取出的empVO物件,存入req
				String url = "/coupon/update_coupon_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_store_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_store_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String co_id = req.getParameter("co_id");

				String co_code = req.getParameter("co_code");
				if (co_code == null || co_code.trim().length() == 0) {
					errorMsgs.add("優惠券代碼: 請勿空白");
				}

				Integer co_amount = null;
				try {
					co_amount = new Integer(req.getParameter("co_amount").trim());
				} catch (NumberFormatException e) {
					co_amount = 0;
					errorMsgs.add("折扣金額請填數字.");
				}

				java.sql.Timestamp co_start = null;
				try {
					co_start = new java.sql.Timestamp(sdf.parse(req.getParameter("co_start")).getTime());
				} catch (IllegalArgumentException e) {
					co_start = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入起始時間!");
				}

				java.sql.Timestamp co_expire = null;
				try {
					co_expire = new java.sql.Timestamp(sdf.parse(req.getParameter("co_expire")).getTime());
				} catch (IllegalArgumentException e) {
					co_expire = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入截止時間!");
				}

				String co_status = req.getParameter("co_status");
				if (co_status == null || co_status.trim().length() == 0) {
					errorMsgs.add("狀態欄:請勿空白");
				}
				String m_id = req.getParameter("m_id");
				if (m_id == null || m_id.trim().length() == 0) {
					errorMsgs.add("會員ID:請勿空白");
				}
				

				CouponVO couponVO = new CouponVO();
				couponVO.setCo_id(co_id);
				couponVO.setCo_code(co_code);
				couponVO.setCo_amount(co_amount);
				couponVO.setCo_start(co_start);
				couponVO.setCo_expire(co_expire);
				couponVO.setCo_status(co_status);
				couponVO.setM_id(m_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/coupon/update_coupon_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				CouponService couponSvc = new CouponService();
				couponVO = couponSvc.updateCoupon(co_id, co_code, co_amount, co_start, co_expire, co_status,m_id);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("couponVO", couponVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/coupon/listOneCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/update_coupon_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String co_code = req.getParameter("co_code");
				if (co_code == null || co_code.trim().length() == 0) {
					errorMsgs.add("優惠券代碼: 請勿空白");
				}

				Integer co_amount = null;
				try {
					co_amount = new Integer(req.getParameter("co_amount").trim());
				} catch (NumberFormatException e) {
					co_amount = 0;
					errorMsgs.add("折扣金額請填數字.");
				}

				java.sql.Timestamp co_start = null;
				try {
					co_start = new java.sql.Timestamp(sdf.parse(req.getParameter("co_start")).getTime());
				} catch (IllegalArgumentException e) {
					co_start = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入起始時間!");
				}

				java.sql.Timestamp co_expire = null;
				try {
					co_expire = new java.sql.Timestamp(sdf.parse(req.getParameter("co_expire")).getTime());
				} catch (IllegalArgumentException e) {
					co_expire = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入截止時間!");
				}

				String co_status = req.getParameter("co_status");
				if (co_status == null || co_status.trim().length() == 0) {
					errorMsgs.add("狀態欄:請勿空白");
				}
				String m_id = req.getParameter("m_id");
				if (m_id == null || m_id.trim().length() == 0) {
					errorMsgs.add("會員ID:請勿空白");
				}
				
				CouponVO couponVO = new CouponVO();
				couponVO.setCo_code(co_code);
				couponVO.setCo_amount(co_amount);
				couponVO.setCo_start(co_start);
				couponVO.setCo_expire(co_expire);
				couponVO.setCo_status(co_status);
				couponVO.setM_id(m_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/coupon/addCoupon.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				CouponService couponSvc = new CouponService();
				couponVO = couponSvc.addCoupon(co_code, co_amount, co_start, co_expire, co_status,m_id);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/coupon/listAllCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/addCoupon.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String co_id = new String(req.getParameter("co_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				CouponService couponSvc = new CouponService();
				couponSvc.deleteCoupon(co_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/coupon/listAllCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
