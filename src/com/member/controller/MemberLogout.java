package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/controller/MemberLogout")
public class MemberLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//為了讓他不會自動生成session
		HttpSession session= req.getSession(false);
		if(session == null) {
			res.sendRedirect(req.getContextPath()+ "/Front_end/members/LoginPage.jsp");
		}
		session.removeAttribute("account");
		session.removeAttribute("loginId");
		session.removeAttribute("loginName");
		res.sendRedirect(req.getContextPath()+ "/Front_end/members/LoginPage.jsp");
	}

}
