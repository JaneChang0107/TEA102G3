package com.productType.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productType.model.ProductTypeService;
import com.productType.model.ProductTypeVO;

@WebServlet("/ProductTypeAjax")
public class ProductTypeAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductTypeAjax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("pppppppppppppptttttttttt");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		ProductTypeService ptService = new ProductTypeService();
		List<ProductTypeVO> ptVOs = ptService.getAll();
		
		ObjectMapper mapper = new ObjectMapper();
		writer.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ptVOs));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
