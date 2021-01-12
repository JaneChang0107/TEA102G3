package com.employee.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.member.model.MemberService;
import com.member.model.MemberVO;


@WebServlet("/Search_m_status")
public class Search_m_status extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		String enter = readJSONString(req);
		try {
			JSONObject jsonObj = new JSONObject(enter);
			String m_status = jsonObj.getString("m_status");
			
			int num_m_status = Integer.parseInt(m_status);
			System.out.println("num_m_status = " + num_m_status);
			
			MemberService service = new MemberService();
			List<MemberVO> list = service.getMemberByStatus(num_m_status);
			for (MemberVO memberVO : list) {
				System.out.println("memberVO="+memberVO);
			}
			System.out.println("list = " + list);
			System.out.println("list長度" + list.size());
			
			JSONObject obj = new JSONObject(list);
			
			System.out.println("obj = " + obj);
			
			String jsonStr = obj.toString();
			
			System.out.println("jsonStr = " + jsonStr);
			
			out.write(jsonStr);
			out.flush();
			out.close();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	/***********************json物件轉string方法***********************/
	public String readJSONString(HttpServletRequest request){
		StringBuffer json = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while((line = reader.readLine()) != null) {
				json.append(line);
			}
		}	catch(Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
