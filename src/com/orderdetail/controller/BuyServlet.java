package com.orderdetail.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.orderdetail.model.OrderdetailService;
import com.orderdetail.model.OrderdetailVO;
import com.orderlist.model.OrderlistService;
import com.orderlist.model.OrderlistVO;
import com.product.model.ProductVO;
import com.productPicture.model.ProductPictureService;
import com.productPicture.model.ProductPictureVO;
import com.productType.model.ProductTypeVO;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

	

		if (action.equals("SUCCESS")) {

			OrderlistVO olvo = new OrderlistVO();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = df.format(timestamp);
			java.sql.Timestamp o_date = java.sql.Timestamp.valueOf(nowTime);
			String o_status = "0";
			String o_transport = req.getParameter("o_transport");
			String o_address = req.getParameter("o_address");
			Integer o_total = Integer.parseInt(req.getParameter("o_total"));
			Integer o_pm = Integer.parseInt(req.getParameter("o_kun"));
			String m_id = (String) session.getAttribute("loginId");

			OrderlistService orderSvc = new OrderlistService();
			olvo = orderSvc.addOrderlistVO2(o_date, o_status, o_transport, o_address, o_total, o_pm, m_id);

			session.setAttribute("shoppingcart", buylist);
			res.sendRedirect(req.getContextPath()+"/Front_end/shoppingCart/checkBuyPageOK.jsp");
			session.removeAttribute("shoppingCart");
			buylist.removeAll(buylist);
			return;

		}

		if (action.equals("CALCULATE")) {

			if (session.getAttribute("loginId") == null) {
				session.setAttribute("shoppingcart", buylist); // 資料庫取出的list物件,存入session
				// Send the Success view
				String url = "/Front_end/members/LoginPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
				successView.forward(req, res);
				return;
			}

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// Send the use back to the form, if there were errors

		
			Integer amount = 0;
			int price = 0;
			Integer q = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO order = buylist.get(i);
				price = order.getP_price();
				String qty = req.getParameter("xx" + (i + 1));

				if (qty == null || (qty.trim()).length() == 0) {
					errorMsgs.add("請輸入數量");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Front_end/shoppingCart/checkBuyPage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				q = Integer.parseInt(qty);

				amount += (price * q);

			}
			req.setAttribute("amount", amount);

			session.setAttribute("shoppingcart", buylist); // 資料庫取出的list物件,存入session
			// Send the Success view
			String url = "/Front_end/shoppingCart/checkBuyPageDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
			successView.forward(req, res);
			return;

		}

		// 刪除購物車中的書籍
		if (action.equals("DELETE")) {
			String del = req.getParameter("del");
			Integer d = Integer.parseInt(del);
			buylist.removeElementAt(d);
			String url = "/Front_end/shoppingCart/checkBuyPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
			successView.forward(req, res);
			return;

		}

		if (action.equals("addCart")) {
			boolean match = false;

			// 取得後來新增的書籍
			ProductVO aproduct = getProductlist(req);

			// 新增第一本書籍至購物車時
			if (buylist == null) {
				buylist = new Vector<ProductVO>();
				buylist.add(aproduct);

			} else {
				for (int i = 0; i < buylist.size(); i++) {
					ProductVO product = buylist.get(i);
//						 假若新增的書籍和購物車的書籍一樣時
					if (product.getP_id().equals(aproduct.getP_id())) {
						buylist.setElementAt(product, i);
						match = true;
					} // end of if name matches
				} // end of for

				// 假若新增的書籍和購物車的書籍不一樣時
				if (!match)
					buylist.add(aproduct);
			}
		}

		session.setAttribute("shoppingcart", buylist);
		String url = "/Front_end/shoppingCart/checkBuyPage.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
		return;
	}

	private ProductVO getProductlist(HttpServletRequest req) {

		String p_id = req.getParameter("p_id");
		String p_name = req.getParameter("p_name");
		String p_price = req.getParameter("p_price");
		String p_kind = req.getParameter("p_kind");
		String p_detail = req.getParameter("p_detail");
		String p_count = req.getParameter("p_count");
		String m_id = req.getParameter("m_id");
		

		ProductVO pvo = new ProductVO();
		ProductTypeVO ptvo = new ProductTypeVO();

		pvo.setP_id(p_id);
		pvo.setP_name(p_name);
		pvo.setP_price(Integer.parseInt(p_price));
		pvo.setPt_id(p_kind);
		pvo.setP_detail(p_detail);
		pvo.setP_count(Integer.parseInt(p_count));
		pvo.setM_id(m_id);
		


		return pvo;
	}

}
