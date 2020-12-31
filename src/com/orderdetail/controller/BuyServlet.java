package com.orderdetail.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductVO;
import com.productPicture.model.ProductPictureVO;
import com.productType.model.ProductTypeVO;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doPost(req,res);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		
		System.out.println("in");
		
		if (action.equals("xxxx")) {
			for(int i=1;i<=buylist.size();i++) {
			System.out.println(req.getParameter("xx"+i));
			
			}
		}
		
		
		// �R���ʪ����������y
		if (action.equals("DELETE")) {
			String del = req.getParameter("del");
			Integer d = Integer.parseInt(del);
			buylist.removeElementAt(d);
		
			}		

		if (action.equals("addCart")) {
			
//			// �s�W���y���ʪ�����
//			else if (action.equals("ADD")) {
				boolean match = false;

				// ���o��ӷs�W�����y
				ProductVO aproduct = getProductlist(req);

				// �s�W�Ĥ@�����y���ʪ�����
				if (buylist == null) {
					buylist = new Vector<ProductVO>();
					buylist.add(aproduct);
					
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						ProductVO product = buylist.get(i);
//						 ���Y�s�W�����y�M�ʪ��������y�@�ˮ�
						if (product.getP_id().equals(aproduct.getP_id())) {
							System.out.println("��J���ƪ��ӫ~�F");
							buylist.setElementAt(product, i);	
							match = true;
						} // end of if name matches
					} // end of for

					// ���Y�s�W�����y�M�ʪ��������y���@�ˮ�
					if (!match)
						buylist.add(aproduct);
				}
			}

			session.setAttribute("shoppingcart", buylist);
			String url = "/Front_end/shoppingCart/checkBuyPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
	

		// ���b�A�p���ʪ������y�����`��
//		else if (action.equals("CHECKOUT")) {
////			float total = 0;
////			for (int i = 0; i < buylist.size(); i++) {
////				BOOK order = buylist.get(i);
////				float price = order.getPrice();
////				int quantity = order.getQuantity();
////				total += (price * quantity);
////			}
//			double total = buylist.stream().mapToDouble(b -> b.getP_price() * b.getP_count()).sum();
//
//			String amount = String.valueOf(total);
//			req.setAttribute("amount", amount);
//			String url = "/Checkout.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
//		}
//	}

	private ProductVO getProductlist(HttpServletRequest req) {
		
		String p_id = req.getParameter("p_id");
		String p_name = req.getParameter("p_name");
		String p_price = req.getParameter("p_price");
		String p_kind = req.getParameter("p_kind");
		String p_detail = req.getParameter("p_detail");
		String p_count = req.getParameter("p_count");
		

		ProductVO pvo = new ProductVO();
		ProductTypeVO ptvo = new ProductTypeVO();
		
		pvo.setP_id(p_id);
		pvo.setP_name(p_name);	
		pvo.setP_price(Integer.parseInt(p_price));
		ptvo.setPt_kind(p_kind);
		pvo.setP_detail(p_detail);
		pvo.setP_count(Integer.parseInt(p_count));
		System.out.println(p_id);
		System.out.println(p_price);
		System.out.println(p_kind);
		
		
		return pvo;
}
	
		

	
}
