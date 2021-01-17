package com.orderlist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bell.model.BellVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderlist.model.OrderlistService;
import com.websocket.WebSocket;

import redis.clients.jedis.Jedis;

@WebServlet("/OrderArrive")
public class OrderArrive extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderArrive() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String oid = request.getParameter("oid");
		
		OrderlistService oService = new OrderlistService();
		
		if(!oid.isEmpty()) {
			oService.updateStatusArrive(oid);
		}
		//推播開始--------------------------
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		
		ObjectMapper mapper = new ObjectMapper();
		WebSocket ws = new WebSocket();
		BellVO bellVO = new BellVO();
		
		bellVO.setM_id(oService.getOneOrderlist(oid).getM_id());
		bellVO.setMessage(" 訂單已到貨");
		
		ws.onMessage(mapper.writeValueAsString(bellVO));
		
		jedis.close();
		//推播結束--------------------------
		request.setAttribute("oid", oid);
		RequestDispatcher rd = request.getRequestDispatcher("/Back_end/orderlist/OrderArrive.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
