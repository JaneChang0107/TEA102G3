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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productPicture.model.ProductPictureService;
import com.productPicture.model.ProductPictureVO;

@WebServlet("/ProductServlet")
@MultipartConfig
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
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
//		找商品
		if("findByName".equals(action)) {
			
			List<String> errors = new LinkedList<String>();
			request.setAttribute("errors", errors);
			ProductService pService = new ProductService();
			String type = request.getParameter("ptype");
			String name = request.getParameter("name");
			List<ProductVO> pVOs = null;
			
			request.setAttribute("type", type);
			request.setAttribute("name", name);
					
			// 都沒有
			if("no".equals(type) && name.trim().isEmpty()) {
				pVOs = pService.getAll();
				request.setAttribute("pVOs", pVOs);
			}
			
			// 搜尋不含種類
			if("no".equals(type) && !name.isEmpty()) {
				pVOs = pService.findProduct(name);
			}
			
			if(!"no".equals(type) && !name.isEmpty()) {
				pVOs = pService.findProduct(name, type);
			}
			
			if(!"no".equals(type) && name.isEmpty()) {
				pVOs = pService.findTypeProduct(type);
			}
			
			if(pVOs == null || pVOs.isEmpty()) {
				errors.add("查無商品");
			}
			
			if(!errors.isEmpty()) {
				RequestDispatcher fail = request.getRequestDispatcher("/Front_end/product/searchSellProduct.jsp");
				fail.forward(request, response);
				return;
			}
			request.setAttribute("pVOs", pVOs);
			RequestDispatcher send = request.getRequestDispatcher("/Front_end/product/searchSellProduct.jsp");
			send.forward(request, response);
		}
		if("insert".equals(action)) {
			
			List<String> errors = new LinkedList<String>();
			request.setAttribute("errors", errors);
			// 驗證格式
			String pname = request.getParameter("pname");
			if(pname == null || pname.trim().isEmpty()) {
				errors.add("請輸入產品名稱");
			}
			
			String ptid = request.getParameter("ptid");
			if(ptid == null || ptid.isEmpty()) {
				errors.add("請選擇商品種類");
			}
			
			Integer pprice = null;
			try {
				pprice = Integer.parseInt(request.getParameter("pprice").trim());
				if(pprice < 0) {
					errors.add("產品價格輸入有誤");
				}
			} catch(NumberFormatException e) {
				pprice = 0;
				errors.add("產品價格輸入有誤");
			}
			
			Integer pcount = null;
			try {
				pcount = Integer.parseInt(request.getParameter("pcount").trim());
				if(pcount <= 0) {
					errors.add("產品數量需大於0");
				}
			} catch(NumberFormatException e) {
				pcount = 0;
				errors.add("數量輸入錯誤");
			}
			Integer pstatus = null;
			try {
				pstatus = Integer.parseInt(request.getParameter("pstatus"));
				if(pstatus < 0 || pstatus > 1) {
					errors.add("請選擇商品上下架");
				}
			} catch(NumberFormatException e) {
				errors.add("請選擇商品上下架");
				e.printStackTrace();
			}
			String pdetail = request.getParameter("pdetail");
			Date d = new Date();
			Timestamp addDate = new Timestamp(d.getTime());
			HttpSession session = request.getSession();
			String mid = (String) session.getAttribute("mid");
			System.out.println(mid);
			
			ProductVO pVO = new ProductVO();
			pVO.setP_name(pname);
			pVO.setP_price(pprice);
			pVO.setP_count(pcount);
			pVO.setPt_id(ptid);
			pVO.setP_detail(pdetail);
			// 有問題退回
			if(!errors.isEmpty()) {
				request.setAttribute("pVO", pVO);
				RequestDispatcher fail = request.getRequestDispatcher("/Front_end/product/addProduct.jsp");
				fail.forward(request, response);
				return;
			}
			// 沒問題開始新增
			ProductService pService = new ProductService();
			ProductPictureService ppService = new ProductPictureService();
			
			pVO = pService.addProduct(pname, pprice, pdetail, ptid, pcount, addDate, pstatus, mid);
			
			// 新增 圖片
			Collection<Part> parts = request.getParts();
			for(Part p : parts) {
				if("img".equals(p.getName()) && p.getSize() != 0) {
					InputStream is = p.getInputStream();
					byte[] b = new byte[is.available()];
					is.read(b);
					ppService.addProductPicture(b, pVO.getP_id());
				}
			}

//	======================暫時暫時暫時暫時暫時暫時===================================================
			List<ProductVO> pVOs = pService.getAll();
			request.setAttribute("pVOs", pVOs);
			RequestDispatcher ok = request.getRequestDispatcher("/Back_end/product/showProduct.jsp");
			ok.forward(request, response);
//	================================================================================================
		}
		
		if("delete".equals(action)) {
			
			String pid = request.getParameter("pid");
			
			ProductService pService = new ProductService();
			ProductPictureService ppService = new ProductPictureService();
			ppService.deleteProductPictureByProduct(pid);
			pService.deleteProduct(pid);
			
			response.sendRedirect(request.getContextPath() + "/ProductServlet?action=showAll");
		}
		
		if("updateOne".equals(action)) {
			
			String pid = request.getParameter("pid");
			
			ProductPictureService ppService = new ProductPictureService(); 
			ProductService pService = new ProductService();
			ProductVO pVO = pService.oneProduct(pid);
			List<ProductPictureVO> ppVOs = ppService.findProductPicture(pid);
			request.setAttribute("ppVOs", ppVOs);
			request.setAttribute("pVO", pVO);
			RequestDispatcher rd = request.getRequestDispatcher("/Front_end/product/editProduct.jsp");
			rd.forward(request, response);
		}
		
		if("update".equals(action)) {
			
			List<String> errors = new LinkedList<String>();
			String pid = request.getParameter("pid");
			request.setAttribute("errors", errors);
			// 驗證格式
			String pname = request.getParameter("pname");
			if(pname == null || pname.trim().isEmpty()) {
				errors.add("請輸入產品名稱");
			}
			
			String ptid = request.getParameter("ptid");
			System.out.println(ptid);
			if(ptid == null || ptid.isEmpty()) {
				errors.add("請選擇商品種類");
			}
			
			Integer pprice = null;
			try {
				pprice = Integer.parseInt(request.getParameter("pprice").trim());
				if(pprice < 0) {
					errors.add("產品價格輸入有誤");
				}
			} catch(NumberFormatException e) {
				pprice = 0;
				errors.add("產品價格輸入有誤");
			}
			
			Integer pcount = null;
			try {
				pcount = Integer.parseInt(request.getParameter("pcount").trim());
				if(pcount <= 0) {
					errors.add("產品數量需大於0");
				}
			} catch(NumberFormatException e) {
				pcount = 0;
				errors.add("數量輸入錯誤");
			}
			
			Integer pstatus = null;
			try {
				pstatus = Integer.parseInt(request.getParameter("pstatus"));
				if(pstatus < 0 || pstatus > 1) {
					errors.add("請選擇商品狀態");
				}
			} catch(NumberFormatException e) {
				errors.add("請選擇商品狀態");
				e.printStackTrace();
			}
			
			String pdetail = request.getParameter("pdetail");
			
			if(pdetail == null || pdetail.isEmpty()) {
				errors.add("請輸入商品介紹");
			}
			
			Date d = new Date();
			Timestamp reviseDate = new Timestamp(d.getTime());
			
			ProductVO pVO = new ProductVO();
			pVO.setP_name(pname);
			pVO.setP_price(pprice);
			pVO.setP_count(pcount);
			pVO.setPt_id(ptid);
			pVO.setP_detail(pdetail);
			// 有問題退回
			if(!errors.isEmpty()) {
				request.setAttribute("pVO", pVO);
				RequestDispatcher fail = request.getRequestDispatcher("/Front_end/product/editProduct.jsp");
				fail.forward(request, response);
				return;
			}
			// 沒問題開始修改
			ProductService pService = new ProductService();
			ProductPictureService ppService = new ProductPictureService();
			
			pVO = pService.updateProduct(pid, pname, pprice, pdetail, ptid, pcount, reviseDate, pstatus);
			
			// 修改圖片
			Collection<Part> parts = request.getParts();
			Integer i = 1;
			for(Part p : parts) {
				String img = "img" + i;
				String ppid = request.getParameter("ppid" + i);
				System.out.println(i);
				System.out.println(ppid);
				if(img.equals(p.getName()) && p.getSize() == 0) {
					++i;
					ProductPictureVO ppVO = ppService.findOneProductPicture(ppid);
					ppService.updateProductPocture(ppid, ppVO.getPp_picture());
				}
				
				if(img.equals(p.getName()) && p.getSize() != 0) {
					++i;
					InputStream is = p.getInputStream();
					byte[] b = new byte[is.available()];
					is.read(b);
					ppService.updateProductPocture(ppid, b);
				}
				
				if("img".equals(p.getName()) && p.getSize() != 0) {
					InputStream is = p.getInputStream();
					byte[] b = new byte[is.available()];
					is.read(b);
					ppService.addProductPicture(b, pid);
				}
			}
//			======================暫時暫時暫時暫時暫時暫時===================================================
				List<ProductVO> pVOs = pService.getAll();
				request.setAttribute("pVOs", pVOs);
				RequestDispatcher ok = request.getRequestDispatcher("/Back_end/product/showProduct.jsp");
				ok.forward(request, response);
//			================================================================================================
			
		}
		
		if("showAll".equals(action)) {
			ProductService pService = new ProductService();
			List<ProductVO> pVOs = pService.getAll();
			request.setAttribute("pVOs", pVOs);
			RequestDispatcher rd = request.getRequestDispatcher("/Back_end/product/showProduct.jsp");
			rd.forward(request, response);
		}
//		單一商品
		if("findthis".equals(action)) {
			String pid = request.getParameter("pid");
			
			ProductService pService = new ProductService();
			ProductVO pVO = pService.oneProduct(pid);
			if(pVO != null) {
				request.setAttribute("pVO", pVO);
				RequestDispatcher rd = request.getRequestDispatcher("/Front_end/product/ProductDetail.jsp");
				rd.forward(request, response);
			}
		}
		
		if("sellerProduct".equals(action)) {
			
			HttpSession session = request.getSession();
			String mid = (String) session.getAttribute("mid");
			ProductService pService = new ProductService();
			System.out.println(mid);
			List<ProductVO> sellerProduct = pService.findBySeller(mid);
			
			ObjectMapper mapper = new ObjectMapper();
			
			String product = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sellerProduct);
			System.out.println(product);
			response.getWriter().println(product);
			
//			request.setAttribute("sellerProduct", sellerProduct);
//			RequestDispatcher rd = request.getRequestDispatcher("/Front_end/product/sellerProduct.jsp");
//			rd.forward(request, response);
			
		}
	}
}
