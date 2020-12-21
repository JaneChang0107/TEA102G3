package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("/Productajax")
public class Productajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public Productajax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String action = request.getParameter("action");
		
		if("showSell".equals(action)) {
			String mid = request.getParameter("mid");
			ProductService pService = new ProductService();
			
			List<ProductVO> pVOs = pService.findBySeller(mid);
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pVOs);
			
			writer.println();
			request.setAttribute("pVOs", pVOs);
			
			
		}
		
	}

}
