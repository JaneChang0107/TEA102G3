package com.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.model.ProductService;
import com.product.model.ProductVO;

@WebServlet("/ProductAjaxChangeStatus")
public class ProductAjaxChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductAjaxChangeStatus() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Integer pstatus = Integer.parseInt(request.getParameter("pstatus"));
		String pid = request.getParameter("pid");
		
		ProductService pService = new ProductService();
		
		if(pstatus == 1 || pstatus == 11) {
			pstatus += 1;
		} else if(pstatus == 2 || pstatus == 12) {
			pstatus -= 1;
		}

		pService.checked(pid, pstatus);
		
		ProductVO pVO = pService.oneProduct(pid);
		
		ObjectMapper mapper = new ObjectMapper();
		
		response.getWriter().println(mapper.writeValueAsString(pVO));
	}
}
