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
				
				// ����
				if(pid == null || pid.isEmpty()) {
					errors.add("�п�J�ӫ~�s��");
				}
	
				if(request.getPart("picture").getSize() == 0) {
					errors.add("�ФW�ǰӫ~�Ϥ�");
				}
	
				// �����D
				if(!errors.isEmpty()) {
					request.setAttribute("pid", pid);
					RequestDispatcher fail = request.getRequestDispatcher("/Back_end/productPicture/addProductPicture.jsp");
					fail.forward(request, response);
					return;
				}
				// �S���D
				ProductPictureService ppSerivce = new ProductPictureService();
				// ���J���
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
			// �R��
			if("delete".equals(action)) {
				List<String> errors = new LinkedList<String>();
				request.setAttribute("errors", errors);
				String ppid = request.getParameter("ppid");

				ProductPictureService ppService = new ProductPictureService();
				ppService.deleteProductPocture(ppid);

				response.sendRedirect(request.getContextPath() + "/Back_end/productPicture/allProductPicture.jsp");
			}

			// ��ܤ@�Ӳ��~
			if("getOne".equals(action)) {
				
				String pid = request.getParameter("pid");
				ProductPictureService ppService = new ProductPictureService();
				List<ProductPictureVO> ppVOs = ppService.findProductPicture(pid);
				System.out.println(pid);
				
				request.setAttribute("ppVOs", ppVOs);
				for(ProductPictureVO p : ppVOs) {
					System.out.println("getone" + p);
				}
				RequestDispatcher ok = request.getRequestDispatcher("/Back_end/productPicture/oneProductPicture.jsp");
				ok.forward(request, response);
			}
			
			// ��s�����
			if("updateOne".equals(action)) {
				List<String> errors = new LinkedList<String>();
				request.setAttribute("errors", errors);
				String ppid = request.getParameter("ppid");
				String pid = request.getParameter("pid");
				if(ppid == null || ppid.isEmpty()) {
					errors.add("�Ϥ���ܿ��~");
				}
				if(!errors.isEmpty()) {
					RequestDispatcher dr = request.getRequestDispatcher("/Back_end/productPicture/oneProductPicture.jsp");
					dr.forward(request, response);
					return;
				}
				ProductPictureVO ppVO = new ProductPictureVO();
				ppVO.setP_id(pid);
				ppVO.setPp_id(ppid);
				request.setAttribute("ppVO", ppVO);
				
				RequestDispatcher dr = request.getRequestDispatcher("/Back_end/productPicture/updateProductPicture.jsp");
				dr.forward(request, response);
			}
			
			// ��s
			if("update".equals(action)) {
//				List<String> errors = new LinkedList<String>();
//				request.setAttribute("errors", errors);
//				
//				if(request.getPart("picture").getSize() == 0) {
//					errors.add("�ФW�ǹϤ�");
//				}
				String ppid = request.getParameter("ppid");
				ProductPictureService ppService = new ProductPictureService();
				
//				if(!errors.isEmpty()) {
//					request.setAttribute("ppVO", ppVO);
//					RequestDispatcher fail = request.getRequestDispatcher("/Back_end/productPicture/updateProductPicture.jsp");
//					fail.forward(request, response);
//					return;
//				}
				
				
				for(Part pt : request.getParts()) {
					if("picture".equals(pt.getName()) && pt.getSize() == 0) {
						ppService.updateProductPocture(ppid, ppService.findOneProductPicture(ppid).getPp_picture());
					}
					
					if("picture".equals(pt.getName()) && pt.getSize() != 0) {
						InputStream is = pt.getInputStream();
						byte[] b = new byte[is.available()];
						is.read(b);
						ppService.updateProductPocture(ppid, b);
					}
				}
				response.sendRedirect(request.getContextPath() + "/Back_end/productPicture/allProductPicture.jsp");
//				RequestDispatcher dr = request.getRequestDispatcher("/Back_end/productPicture/allProductPicture.jsp");
//				dr.forward(request, response);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
