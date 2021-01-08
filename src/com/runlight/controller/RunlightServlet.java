package com.runlight.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
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

		
		
		if ("show".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {	
				String str = req.getParameter("key");
				
				String keyReg = "^[(alM0-9_)]{3,6}$";
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("�п�Jall�t�Τ��i�άO�|���s��");
				} else if(!str.trim().matches(keyReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�u���Jall�άOM00001");
	            }
				
				String value = req.getParameter("value").trim();
				if (value == null || value.trim().length() == 0) {
					errorMsgs.add("���e�ФŪť�");
				}	
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/Back_end/runlight/input.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
			ObjectMapper mapper = new ObjectMapper();
			WebSocket ws = new WebSocket();
			BellVO bellVO = new BellVO();
			
			bellVO.setM_id(req.getParameter("key"));
			bellVO.setMessage(req.getParameter("value"));
			
			ws.onMessage(mapper.writeValueAsString(bellVO));
			
			res.sendRedirect(req.getContextPath() + "/Back_end/runlight/input.jsp");
			}catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Back_end/runlight/input.jsp");
				failureView.forward(req, res);
			}
			
			
			
			
			}
		
		if("announcement".equals(action)) {
			
			Jedis jedis = new Jedis("localhost", 6379);
			jedis.auth("123456");
			
			
			String mid = (String) req.getSession().getAttribute("loginId");
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<String> rl1 = jedis.lrange("BellMessage:" + mid, 0, -1);
			System.out.println(mid);
			System.out.println(rl1);

			res.getWriter().write(mapper.writeValueAsString(rl1));
			
			
			jedis.close();
			
		}
		if("delete".equals(action)) {
			Jedis jedis = new Jedis("localhost", 6379);
			jedis.auth("123456");
			String mid = (String) req.getSession().getAttribute("loginId");
			jedis.del("BellMessage:" + mid);
			
			jedis.close();
			
		}

	}

}
