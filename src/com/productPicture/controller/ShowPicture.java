package com.productPicture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.productPicture.model.ProductPictureService;
import com.productPicture.model.ProductPictureVO;

@WebServlet("/ShowPicture")
public class ShowPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowPicture() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/gif");
		String type =request.getParameter("type");
		String id = request.getParameter("id");
		ServletOutputStream os = response.getOutputStream();
		// ������������Ϥ�
		if("pp".equals(type)) {
			ProductPictureService ppService = new ProductPictureService();
			ProductPictureVO ppVO = ppService.findOneProductPicture(id);
			
			if(ppVO != null) {
				byte[] b = ppVO.getPp_picture();
				byte[] smallPicture = ImageUtil.shrink(b, 200);
				os.write(smallPicture);
			}
			os.close();
		}
		
		if("ppid".equals(type)) {
			ProductPictureService ppService = new ProductPictureService();
			ProductPictureVO ppVO = ppService.findOneProductPicture(id);
			
			byte[] b = ppVO.getPp_picture();
			os.write(b);
			os.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
