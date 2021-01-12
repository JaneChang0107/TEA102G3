package com.qrcode;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/QRCode")
public class OrderListQRCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderListQRCode() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String oid = request.getParameter("oid");
		
		OrderListQRCodeCreate qr = new OrderListQRCodeCreate();
		
		response.getWriter().println(qr.creater(oid));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
