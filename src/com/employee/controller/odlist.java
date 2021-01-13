package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.orderdetail.model.*;


@WebServlet("/odlist")
public class odlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
   

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("test/html; charset=UTF-8");
		PrintWriter  out = res.getWriter();
		
		OrderdetailService odSVC= new OrderdetailService();
		List<OrderdetailVO> list = odSVC.getAll();
		JSONObject jobj=new JSONObject();
		try {
			jobj.put("odlist", list);
			out.write(jobj.toString());
			out.flush();
			out.close();
			
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
