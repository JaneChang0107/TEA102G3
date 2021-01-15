package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.bell.model.BellVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.websocket.WebSocket;


@WebServlet("/Send")
public class Send extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {
		WebSocket ws = new WebSocket();
		ws.userList();
	}
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("test/html; charset=UTF-8");
		
		List<String> errorMsgs = new LinkedList<String>();

		String str = req.getParameter("key");
		
		String keyReg = "^[(alM0-9_)]{3,6}$";
		if (str == null || str.trim().length() == 0) {
			errorMsgs.add("請輸入all系統公告或是會員編號");
		} else if(!str.trim().matches(keyReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("只能輸入all或是M00001");
        }
		
		String value = req.getParameter("value").trim();
		if (value == null || value.trim().length() == 0) {
			errorMsgs.add("內容請勿空白");
		}	
		
		System.out.println("str=" + str);
		System.out.println("value=" + value);
		
		PrintWriter out =res.getWriter();
		MemberService mSVC=new MemberService();
		List<MemberVO> mlist = mSVC.getAll();
		JSONObject jobj =new JSONObject();
		try {
			jobj.put("mid_list", mlist);
			out.write(jobj.toString());
			out.flush();
			out.close();
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	ObjectMapper mapper = new ObjectMapper();
	WebSocket ws = new WebSocket();
	BellVO bellVO = new BellVO();
	
	bellVO.setM_id(req.getParameter("key"));
	bellVO.setMessage(req.getParameter("value"));
	
	ws.onMessage(mapper.writeValueAsString(bellVO));
	}

}
