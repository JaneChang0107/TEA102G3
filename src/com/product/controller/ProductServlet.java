package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productPicture.model.ProductPictureService;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("findByName".equals(action)) {
			List<String> errors = new LinkedList<String>();
			request.setAttribute("errors", errors);
			String type = request.getParameter("type");
			String name = request.getParameter("name");
			// �����O
			if(name.trim().isEmpty()) {
				errors.add("�п�J���~�W��");
			}
			ProductService pService = new ProductService();
			// �j�M���t����
			if("no".equals(type)) {
				List<ProductVO> pVOs = pService.findProduct(name);
				request.setAttribute("pVOs", pVOs);
				if(pVOs == null || pVOs.isEmpty()) {
					errors.add("�d�L�ӫ~");
				}
			}
			
			if(!"no".equals(type)) {
				List<ProductVO> pVOs = pService.findProduct(name, type);
				request.setAttribute("pVOs", pVOs);
				if(pVOs == null || pVOs.isEmpty()) {
					errors.add("�d�L�ӫ~");
				}
			}
			if(!errors.isEmpty()) {
				RequestDispatcher fail = request.getRequestDispatcher("/Back_end/product/oneProduct.jsp");
				fail.forward(request, response);
				return;
			}
			RequestDispatcher send = request.getRequestDispatcher("/Back_end/product/showProduct.jsp");
			send.forward(request, response);
		}
		
		if("insert".equals(action)) {
			
			List<String> errors = new LinkedList<String>();
			request.setAttribute("errors", errors);
			// ���Ү榡
			String pname = request.getParameter("pname");
			if(pname == null || pname.trim().isEmpty()) {
				errors.add("�п�J���~�W��");
			}
			
			String ptid = request.getParameter("ptid");
			if(ptid == null || ptid.isEmpty()) {
				errors.add("�п�ܰӫ~����");
			}
			
			Integer pprice = null;
			try {
				pprice = Integer.parseInt(request.getParameter("pprice").trim());
				if(pprice < 0) {
					errors.add("���~�����J���~");
				}
			} catch(NumberFormatException e) {
				pprice = 0;
				errors.add("���~�����J���~");
			}
			
			Integer pcount = null;
			try {
				pcount = Integer.parseInt(request.getParameter("pcount").trim());
				if(pcount < 0) {
					errors.add("���~�����J���~");
				}
			} catch(NumberFormatException e) {
				pcount = 0;
				errors.add("�ƶq��J���~");
			}
			Integer pstatus = null;
			try {
				pstatus = Integer.parseInt(request.getParameter("pstatus"));
				if(pstatus != 0 || pstatus != 1) {
					errors.add("�п�ܰӫ~���A");
				}
			} catch(NumberFormatException e) {
				errors.add("�п�ܰӫ~���A");
				e.printStackTrace();
			}
			String pdetail = request.getParameter("pdetail");
			Date d = new Date();
			Timestamp addDate = new Timestamp(d.getTime());
			String mid = request.getParameter("mid");
			
			ProductVO pVO = new ProductVO();
			pVO.setP_name(pname);
			pVO.setP_price(pprice);
			pVO.setP_count(pcount);
			pVO.setPt_id(ptid);
			pVO.setP_detail(pdetail);
			// �����D�h�^
			if(!errors.isEmpty()) {
				request.setAttribute("pVO", pVO);
				RequestDispatcher fail = request.getRequestDispatcher("/Front_end/product/addProduct.jsp");
				fail.forward(request, response);
				return;
			}
			// �S���D�}�l�s�W
			ProductService pService = new ProductService();
			ProductPictureService ppService = new ProductPictureService();
			
			pVO = pService.addProduct(pname, pprice, pdetail, ptid, pcount, addDate, pstatus, mid);
			
			// �s�W�Ϥ�
			Collection<Part> parts = request.getParts();
			for(Part p : parts) {
				if("img".equals(p.getName()) && p.getSize() != 0) {
					InputStream is = p.getInputStream();
					byte[] b = new byte[is.available()];
					is.read(b);
					ppService.addProductPicture(b, pVO.getP_id());
				}
			}
			response.sendRedirect("/Front_end/product/sellerProduct.jsp");
		}
		
		if("showSell".equals(action)) {
			String mid = request.getParameter("mid");
			ProductService pService = new ProductService();
			
			List<ProductVO> pVOs = pService.findBySeller(mid);
			request.setAttribute("pVOs", pVOs);
			
			
		}
		
	}
}
