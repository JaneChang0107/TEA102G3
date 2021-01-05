package com.runlight.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bell.model.BellVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderdetail.model.OrderdetailService;
import com.orderdetail.model.OrderdetailVO;
import com.websocket.WebSocket;

import redis.clients.jedis.Jedis;

@WebServlet("/light.do")
public class RunlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {

			Jedis jedis = new Jedis("localhost", 6379);
			jedis.auth("123456");
//			System.out.println(jedis.ping());
			String key = req.getParameter("key");
//			System.out.println(key);
			String value = req.getParameter("value");
//			System.out.println(value);
			jedis.set(key, value);

			String rl = jedis.get(key);
			req.setAttribute("rl", rl);

			jedis.close();
			WebSocket ws = new WebSocket();
			ws.userList();

			res.sendRedirect(req.getContextPath() + "/Back_end/runlight/index.jsp");
		}
		
		if ("show".equals(action)) {
			
			ObjectMapper mapper = new ObjectMapper();
			WebSocket ws = new WebSocket();
			BellVO bellVO = new BellVO();
			
			bellVO.setM_id(req.getParameter("key"));
			bellVO.setMessage(req.getParameter("value"));
			
			ws.onMessage(mapper.writeValueAsString(bellVO));
			
			res.sendRedirect(req.getContextPath() + "/Back_end/runlight/index.jsp");

		}
		
		if("announcement".equals(action)) {
			
			Jedis jedis = new Jedis("localhost", 6379);
			jedis.auth("123456");
			
//			OrderdetailService orderdetailSvc1 = new OrderdetailService();
			
			String mid = (String) req.getSession().getAttribute("loginId");
			
//			List<OrderdetailVO> list = orderdetailSvc1.count();
			ObjectMapper mapper = new ObjectMapper();
			
			List<String> rl1 = jedis.lrange("BellMessage:" + mid, 0, -1);
			System.out.println(mid);
			System.out.println(rl1);
			
			res.getWriter().write(mapper.writeValueAsString(rl1));
			
			jedis.close();
			
		}

	}

}
