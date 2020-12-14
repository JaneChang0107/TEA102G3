package com.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// insert行為符合，執行以下
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 1.接收請求參數，輸入格式錯誤處理
			String m_mail = req.getParameter("m_mail");
			String m_mailReg = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
			if (m_mail == null || m_mail.trim().length() == 0) {
				errorMsgs.add("郵箱請勿空白");
			} else if (!m_mail.trim().matches(m_mailReg)) {
				errorMsgs.add("郵箱不符合格式!請重新輸入");
			}

			String m_password = req.getParameter("m_password").trim();
//          String m_passwordReg="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
			if (m_password == null || m_password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}

			String m_name = req.getParameter("m_name");
			String m_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (m_name == null || m_name.trim().length() == 0) {
				errorMsgs.add("姓名請勿空白");
			} else if (!m_name.trim().matches(m_nameReg)) {
				errorMsgs.add("姓名只能為中英文、數字和_ , 且長度必需在2到10之間");
			}
			
			String m_gender= req.getParameter("m_gender");
			if(m_gender == null || m_name.trim().length()==0) {
				errorMsgs.add("請選擇性別欄");
			}
			
			String m_phone =req.getParameter("m_phone");
			if(m_phone == null || m_phone.trim().length()==0) {
				errorMsgs.add("電話請勿空白");
			}
			
			String m_address =req.getParameter("m_address");
			if(m_address ==null || m_address.trim().length()==0) {
				errorMsgs.add("地址請勿空白");				
			}
			
			String m_birth =req.getParameter("m_birth");
			if(m_birth ==null || m_birth.trim().length()==0) {
				errorMsgs.add("請選擇生日");
			}

		}

	}

}
