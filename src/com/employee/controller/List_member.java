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
import org.json.JSONObject;

import com.employee.model.EmployeeService;
import com.member.model.MemberService;
import com.member.model.MemberVO;


@WebServlet("/List_member")
public class List_member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		MemberService service = new MemberService();
		List<MemberVO> list =  service.getAll();
//		System.out.println(list);
		
		JSONArray jsonAry = new JSONArray(list);
//		System.out.println(jsonAry);
		String jsonStr = jsonAry.toString();
		out.write(jsonStr);
		out.flush();
		out.close();
	}

}
