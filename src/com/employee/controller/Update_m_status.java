package com.employee.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.member.model.MemberService;
import com.member.model.MemberVO;


@WebServlet("/Update_m_status")
public class Update_m_status extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		String enter = readJSONString(req);
		try {
			JSONObject jsonObj = new JSONObject(enter);
			String m_status = jsonObj.getString("m_status");
			String m_id = jsonObj.getString("m_id");
			String m_email = jsonObj.getString("m_email");
			int num_m_status = Integer.parseInt(m_status);
			System.out.println("num_m_status = " + num_m_status);
			System.out.println("m_id = " + m_id);
			
			MemberService service = new MemberService();
			service.updateStatus(num_m_status, m_email);
			
			MemberVO memberVO = service.findOneMem(m_id);
			num_m_status = memberVO.getM_status();
//			m_status = Integer.toString(num_m_status);
//			System.out.println(m_status);
			JSONObject obj = new JSONObject();
			obj.put("m_status", num_m_status);
			System.out.println("obj = " + obj);
			String jsonStr = obj.toString();
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
