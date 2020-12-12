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
		
		// �s�W��
		if("insert".equals(action)) {
			List<String> error = new LinkedList<String>();
			request.setAttribute("error", error);
			
			String platform = request.getParameter("platform").trim();
			String platformRegex = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,100}$";
			// ����
			if(platform == null || platform.isEmpty()) {
				error.add("�п�J���x");
			} else if(!platform.matches(platformRegex)) {
					error.add("���x�榡�����T");
			}
			
			String kind = request.getParameter("kind");
			String kindRegex = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,100}$";
			// ����
			if(kind == null || kind.isEmpty()) {
				error.add("�п�J����");
			} else if(!platform.matches(kindRegex)) {
					error.add("�����榡�����T");
			}
			// ���J���
			ProductTypeVO ptVO = new ProductTypeVO();
			ptVO.setPt_platform(platform);
			ptVO.setPt_kind(kind);
			// �T�{�S�����~�T��
			// �����~�T��
			if(!error.isEmpty()) {
				request.setAttribute("ptVO", ptVO);
				RequestDispatcher fail = request.getRequestDispatcher("/Back_end/productType/addProductType.jsp");
				fail.forward(request, response);
				return;
			}
			// �S���~�T��
			ProductTypeService ptService = new ProductTypeService();
			ptService.addProductType(platform, kind);
			RequestDispatcher ok = request.getRequestDispatcher("/Back_end/productType/allProductType.jsp");
			ok.forward(request, response);
		}
		
		// �ק�
		if("update".equals(action)) {
			List<String> error = new LinkedList<String>();
			request.setAttribute("error", error);
			
			String pt_id = request.getParameter("pt_id");
			String platform = request.getParameter("platform");
			String platformRegex = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,100}$";
			// ����
			if(platform == null || platform.isEmpty()) {
				error.add("�п�J���x");
			} else if ("platform".matches(platformRegex)){
				error.add("���x�榡�����T");
			}
			
			String kind = request.getParameter("kind");
			
			if(platform == null || platform.isEmpty()) {
				error.add("�п�J����");
			} else if ("platform".matches(platformRegex)){
				error.add("�����榡�����T");
			}
			
			if(!error.isEmpty()) {
				
			}
			
		}
	}

}
