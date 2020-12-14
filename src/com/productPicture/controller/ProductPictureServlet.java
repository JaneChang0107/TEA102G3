package com.productPicture.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.productPicture.model.ProductPictureService;
import com.productPicture.model.ProductPictureVO;

@WebServlet("/ProductPictureServlet")
@MultipartConfig
public class ProductPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProductPictureServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		try {
			
		
			if("insert".equals(action)) {
				List<String> errors = new LinkedList<String>();
				request.setAttribute("errors", errors);
				String pid = request.getParameter("pid");
				
				// 驗證
				if(pid == null || pid.isBlank()) {
					errors.add("請輸入商品編號");
				}
	
				if(request.getPart("picture") == null) {
					errors.add("請上傳商品圖片");
				}
	
				// 有問題
				if(!errors.isEmpty()) {
					request.setAttribute("pid", pid);
					RequestDispatcher fail = request.getRequestDispatcher("/Back_end/productPicture/addProductPicture.jsp");
					fail.forward(request, response);
					return;
				}
				// 沒問題
				ProductPictureService ppSerivce = new ProductPictureService();
				// 插入資料
				Collection<Part> pts = request.getParts();
				for(Part pt : pts) {
					if("picture".equals(pt.getName())) {
						InputStream is = pt.getInputStream();
						byte[] b = new byte[is.available()];
						is.read(b);
						is.close();
	//					b = java.util.Base64.getEncoder().encode(b);
						ppSerivce.addProductPicture(b, pid);
					}
				}
				response.sendRedirect(request.getContextPath() + "/Back_end/productPicture/allProductPicture.jsp");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
