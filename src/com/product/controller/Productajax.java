package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productPicture.model.ProductPictureService;
import com.productPicture.model.ProductPictureVO;

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
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = response.getWriter();
		String type = request.getParameter("type");
		String name = request.getParameter("pname");

//		商品資訊
		if(type != null && "no".equals(type)) {
			ProductService pService = new ProductService();
			List<ProductVO> pVOs = null;
			
			if("".equals(name)) {
				pVOs = pService.getAll();
			}
			
			if(!"".equals(name)) {
				System.out.println(name);
				pVOs = pService.findProduct(name);
			}

			ObjectMapper mapper = new ObjectMapper();

			String productJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pVOs);
			
			System.out.println(productJSON);
			writer.println(productJSON);
		}
		
		if(type != null && !"no".equals(type)) {
			ProductService pService = new ProductService();
			
			List<ProductVO> pVOs = pService.findProduct(name, type);
			
			ObjectMapper mapper = new ObjectMapper();
			String productJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pVOs);
			
			writer.println(productJSON);
			
		}
//		拿商品圖片
		String pid = request.getParameter("pid");
		System.out.println(pid);
		if(pid != null) {
			
			ProductPictureService ppService = new ProductPictureService();
			
			int which = 0;
			List<ProductPictureVO> ppVOs = ppService.findProductPicture(pid);
			if(!ppVOs.isEmpty()) {
				which = (int) (Math.random() * ppVOs.size());
				System.out.println(pid);
				System.out.println(which);
				System.out.println(ppVOs.get(which).getPp_id());
				writer.println(ppVOs.get(which).getPp_id());
			} else {
				writer.println("");
			}
			
		}
	}
}
