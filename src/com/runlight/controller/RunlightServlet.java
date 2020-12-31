package com.runlight.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

@WebServlet("/light.do")
public class RunlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
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

			String url = "/Front_end/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}

}
