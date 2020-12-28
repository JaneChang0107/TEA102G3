package com.employee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//false是為了不要再自動長session出來
		HttpSession session = req.getSession(false);
		if(session == null) {
			res.sendRedirect(req.getContextPath() + "/Back_end/employee/login.jsp");
		}
		
		session.removeAttribute("e_id");
		res.sendRedirect(req.getContextPath() + "/Back_end/employee/login.jsp");
	}

}
