package com.productType.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productType.model.ProductTypeService;
import com.productType.model.ProductTypeVO;


@WebServlet("/ProductTypeServlet")
public class ProductTypeServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProductTypeServelt() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		// 新增用
		if("insert".equals(action)) {
			List<String> error = new LinkedList<String>();
			request.setAttribute("error", error);
			
			String platform = request.getParameter("platform").trim();
			String platformRegex = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,100}$";
			// 驗證
			if(platform == null || platform.isEmpty()) {
				error.add("請輸入平台");
			} else if(!platform.matches(platformRegex)) {
					error.add("平台格式不正確");
			}
			
			String kind = request.getParameter("kind");
			String kindRegex = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,100}$";
			// 驗證
			if(kind == null || kind.isEmpty()) {
				error.add("請輸入種類");
			} else if(!platform.matches(kindRegex)) {
					error.add("種類格式不正確");
			}
			// 插入資料
			ProductTypeVO ptVO = new ProductTypeVO();
			ptVO.setPt_platform(platform);
			ptVO.setPt_kind(kind);
			// 確認沒有錯誤訊息
			// 有錯誤訊息
			if(!error.isEmpty()) {
				request.setAttribute("ptVO", ptVO);
				RequestDispatcher fail = request.getRequestDispatcher("/Back_end/productType/addProductType.jsp");
				fail.forward(request, response);
				return;
			}
			// 沒錯誤訊息
			ProductTypeService ptService = new ProductTypeService();
			ptService.addProductType(platform, kind);
			RequestDispatcher ok = request.getRequestDispatcher("/Back_end/productType/allProductType.jsp");
			ok.forward(request, response);
		}
		
		// 修改
		if("update".equals(action)) {
			List<String> error = new LinkedList<String>();
			request.setAttribute("error", error);
			
			String pt_id = request.getParameter("pt_id");
			String platform = request.getParameter("platform");
			String platformRegex = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,100}$";
			// 驗證
			if(platform == null || platform.isEmpty()) {
				error.add("請輸入平台");
			} else if ("platform".matches(platformRegex)){
				error.add("平台格式不正確");
			}
			
			String kind = request.getParameter("kind");
			
			if(platform == null || platform.isEmpty()) {
				error.add("請輸入種類");
			} else if ("platform".matches(platformRegex)){
				error.add("種類格式不正確");
			}
			
			if(!error.isEmpty()) {
				
			}
			
		}
	}

}
