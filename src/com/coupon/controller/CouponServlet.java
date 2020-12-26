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

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("co_id");

				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�u�f��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String co_id = null;
				try {
					co_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�u�f��s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				CouponService couponSvc = new CouponService();
				CouponVO couponVO = couponSvc.getOneCoupon(co_id);
				if (couponVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("couponVO", couponVO); // ��Ʈw���X��couponVO����,�s�Jreq
				String url = "/coupon/listOneCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneCoupon.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllCoupon.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String co_id = new String(req.getParameter("co_id"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				CouponService couponSvc = new CouponService();
				CouponVO couponVO = couponSvc.getOneCoupon(co_id);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("couponVO", couponVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/coupon/update_coupon_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_store_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_store_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String co_id = req.getParameter("co_id");

				String co_code = req.getParameter("co_code");
				if (co_code == null || co_code.trim().length() == 0) {
					errorMsgs.add("�u�f��N�X: �ФŪť�");
				}

				Integer co_amount = null;
				try {
					co_amount = new Integer(req.getParameter("co_amount").trim());
				} catch (NumberFormatException e) {
					co_amount = 0;
					errorMsgs.add("�馩���B�ж�Ʀr.");
				}

				java.sql.Timestamp co_start = null;
				try {
					co_start = new java.sql.Timestamp(sdf.parse(req.getParameter("co_start")).getTime());
				} catch (IllegalArgumentException e) {
					co_start = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�_�l�ɶ�!");
				}

				java.sql.Timestamp co_expire = null;
				try {
					co_expire = new java.sql.Timestamp(sdf.parse(req.getParameter("co_expire")).getTime());
				} catch (IllegalArgumentException e) {
					co_expire = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�I��ɶ�!");
				}

				String co_status = req.getParameter("co_status");
				if (co_status == null || co_status.trim().length() == 0) {
					errorMsgs.add("���A��:�ФŪť�");
				}
				String m_id = req.getParameter("m_id");
				if (m_id == null || m_id.trim().length() == 0) {
					errorMsgs.add("�|��ID:�ФŪť�");
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
					req.setAttribute("couponVO", couponVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/coupon/update_coupon_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				CouponService couponSvc = new CouponService();
				couponVO = couponSvc.updateCoupon(co_id, co_code, co_amount, co_start, co_expire, co_status,m_id);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("couponVO", couponVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/coupon/listOneCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/update_coupon_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String co_code = req.getParameter("co_code");
				if (co_code == null || co_code.trim().length() == 0) {
					errorMsgs.add("�u�f��N�X: �ФŪť�");
				}

				Integer co_amount = null;
				try {
					co_amount = new Integer(req.getParameter("co_amount").trim());
				} catch (NumberFormatException e) {
					co_amount = 0;
					errorMsgs.add("�馩���B�ж�Ʀr.");
				}

				java.sql.Timestamp co_start = null;
				try {
					co_start = new java.sql.Timestamp(sdf.parse(req.getParameter("co_start")).getTime());
				} catch (IllegalArgumentException e) {
					co_start = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�_�l�ɶ�!");
				}

				java.sql.Timestamp co_expire = null;
				try {
					co_expire = new java.sql.Timestamp(sdf.parse(req.getParameter("co_expire")).getTime());
				} catch (IllegalArgumentException e) {
					co_expire = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�I��ɶ�!");
				}

				String co_status = req.getParameter("co_status");
				if (co_status == null || co_status.trim().length() == 0) {
					errorMsgs.add("���A��:�ФŪť�");
				}
				String m_id = req.getParameter("m_id");
				if (m_id == null || m_id.trim().length() == 0) {
					errorMsgs.add("�|��ID:�ФŪť�");
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
					req.setAttribute("couponVO", couponVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/coupon/addCoupon.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				CouponService couponSvc = new CouponService();
				couponVO = couponSvc.addCoupon(co_code, co_amount, co_start, co_expire, co_status,m_id);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/coupon/listAllCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/addCoupon.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String co_id = new String(req.getParameter("co_id"));

				/*************************** 2.�}�l�R����� ***************************************/
				CouponService couponSvc = new CouponService();
				couponSvc.deleteCoupon(co_id);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/coupon/listAllCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
