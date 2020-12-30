package com.runlight.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

public class RunlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	
	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {
		
			Jedis jedis = new Jedis("localhost", 6379);
			jedis.auth("123456");
			System.out.println(jedis.ping());
			String key=req.getParameter("key");
			System.out.println(key);
			String value=req.getParameter("value");
			System.out.println(value);
			jedis.set(key,value);
			
			String url = "/back_end/runlight/light.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			jedis.close();
			
			
		}
		
		
		
//	        String bookList = jedis.get("bookList");
//	        if(null == bookList || "".equals(bookList)){
////	          查詢資料庫
//	            String mysqlDate="data";
////	            將mysqlDate轉成json陣列串
//	            jedis.set("bookList",mysqlDate);
//	            bookList=  jedis.get("bookList");
//	            req.setAttribute("msg","走了資料庫拿資料");
//	            req.setAttribute("bookList",bookList);
//	            req.getRequestDispatcher("/bookList.jsp").forward(req,resp);
//	        }else{
//	            req.setAttribute("msg","直接從redis中取得資料");
//	            req.setAttribute("bookList",bookList);
//	            req.getRequestDispatcher("/bookList.jsp").forward(req,resp);
//	        }
//	    }
//		
//		
//		
		}

}
