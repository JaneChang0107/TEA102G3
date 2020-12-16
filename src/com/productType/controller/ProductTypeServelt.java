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

		request.setCharacterEncoding("Big5");
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
			response.sendRedirect(request.getContextPath() + "/Back_end/productType/allProductType.jsp");
//			String url = "/Back_end/productType/allProductType.jsp";
//			RequestDispatcher ok = request.getRequestDispatcher(url);
//			ok.forward(request, response);
		}
		
		// 修改
		if("update".equals(action)) {
			List<String> error = new LinkedList<String>();
			request.setAttribute("error", error);
			
			String ptid = request.getParameter("pt_id");
			String platform = request.getParameter("platform");
			String platformRegex = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,100}$";
			
			// 驗證
			if(platform == null || platform.isEmpty()) {
				error.add("請輸入平台");
			} else if (!platform.matches(platformRegex)){
				error.add("平台格式不正確");
			}
			
			String kind = request.getParameter("kind");
			
			if(kind == null || kind.isEmpty()) {
				error.add("請輸入種類");
			} else if (!kind.matches(platformRegex)){
				error.add("種類格式不正確");
				System.out.println(kind);
			}
			ProductTypeVO ptVO = new ProductTypeVO();
			ptVO.setPt_platform(platform);
			ptVO.setPt_kind(kind);
			
			if(!error.isEmpty()) {
				request.setAttribute("ptVO", ptVO);
				RequestDispatcher fail = request.getRequestDispatcher("/Back_end/productType/updateProductType.jsp");
				fail.forward(request, response);
				return;
			}
			
			ProductTypeService ptService = new ProductTypeService();
			ptService.updateProductType(ptid, platform, kind);
			response.sendRedirect(request.getContextPath() + "/Back_end/productType/allProductType.jsp");
			
//			RequestDispatcher ok = request.getRequestDispatcher("/Back_end/productType/allProductType.jsp");
//			ok.forward(request, response);
		}
		//  更新單一
		if("updateOne".equals(action)) {
			// 拿取修改id
			String ptid = (String) request.getParameter("pt_id");
			// 用id拿資料
			ProductTypeService ptService = new ProductTypeService();
			ProductTypeVO ptVO = ptService.getOneProductType(ptid);
			request.setAttribute("ptVO", ptVO);
			RequestDispatcher ok = request.getRequestDispatcher("/Back_end/productType/updateProductType.jsp");
			ok.forward(request, response);
		}
		// 刪除
		if("delete".equals(action)) {
			ProductTypeService ptService = new ProductTypeService();
			String ptid = request.getParameter("pt_id");
			ptService.deleteProductType(ptid);
			response.sendRedirect(request.getContextPath() + "/Back_end/productType/allProductType.jsp");
		}
		// 搜尋一個
		if("getOne".equals(action)) {
			ProductTypeService ptService = new ProductTypeService();
			ProductTypeVO ptVO = ptService.getOneProductType(request.getParameter("ptid"));
			request.setAttribute("ptVO", ptVO);
			RequestDispatcher ok = request.getRequestDispatcher("/Back_end/productType/oneProductType.jsp");
			ok.forward(request, response);
		}
	}
}
