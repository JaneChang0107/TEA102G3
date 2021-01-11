package com.websocketchat.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.websocketchat.jedis.JedisHandleMessage;

@WebServlet("/websocketchat/NameServlet")
public class NameServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		String userName = req.getParameter("userName");

		String m_id = req.getParameter("m_id");

		MemberService memSvc = new MemberService();
		MemberVO memberVO = memSvc.findOneMem(m_id);
		HttpSession session = req.getSession();
//		String userId=(String) session.getAttribute("loginId");
//		System.out.println(userId);
		
		String userName = (String) session.getAttribute("loginId");
		
//		Set<String> chatrooms = JedisHandleChatroom.getAllChatrooms(userName);
//		System.out.println(chatrooms.size()+"3221234");
		
//		req.setAttribute("chatrooms", chatrooms);
		req.setAttribute("userName", userName);
		req.setAttribute("memberVO", memberVO);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/chat/chat.jsp");

		dispatcher.forward(req, res);
	}
}
