package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberJDBCDAO;
import com.member.model.MemberVO;

@WebServlet("/member/controller/MemberLogin")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//宣告登入Id及Name之後存入session
	String loginId;
	String loginName;
	
	protected boolean loginAccess(String account, String password) {
		MemberJDBCDAO dao =new MemberJDBCDAO();
		MemberVO memberLogin=dao.getMemberPw(account);
		
		try {
			loginId =memberLogin.getM_id();
			loginName =memberLogin.getM_name();
		if (memberLogin.getM_email().equals(account) && memberLogin.getM_password().equals(password))
			return true;
		else
			return false;
		}catch(Exception e ) {
			System.out.println("重新登入");
		}
		return false;
}
		
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("Big5");
		res.setContentType("text/html; charset=Big5");

		PrintWriter out = res.getWriter();
		String account = req.getParameter("account").trim();
		String password = req.getParameter("password").trim();
		if (!loginAccess(account, password)) {
			out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
			out.println("<BODY>帳號或密碼有誤，請重新輸入!<BR>");
			out.println("請重新登入 <A HREF=" + req.getContextPath() + "/Front_end/members/LoginPage.jsp>重新登入</A>");
			out.println("</BODY></HTML>");
		}
		else {
			HttpSession session = req.getSession();
			session.setAttribute("account", account);
			session.setAttribute("loginId", loginId);
			session.setAttribute("loginName", loginName);
			
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}
			res.sendRedirect(req.getContextPath() + "/Front_end/members/loginSuccess.jsp");
		}

	}

}
