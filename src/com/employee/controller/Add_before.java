package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.store.model.StoreService;
import com.store.model.StoreVO;

@WebServlet("/Add_before")
public class Add_before extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		StoreService storeService = new StoreService();
		List<StoreVO> store_list = storeService.getAll();
		
		JSONArray jsonArray = new JSONArray(store_list);
		String jsonString = jsonArray.toString();

		
		out.println(jsonArray);
		out.flush();
		out.close();
	}

}
