package com.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.member.model.MemberService;

@WebServlet("/MemberChangeEmail")
public class MemberChangeEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberChangeEmail() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req,res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String readjson=readJSONString(req);
		JSONObject jsonobject;
		try {
			jsonobject = new JSONObject(readjson);
			String m_id =jsonobject.getString("m_id");
			String m_email= jsonobject.getString("m_email");
			
			MemberService memSvc =new MemberService();
			memSvc.updateEmail(m_email,m_id);
			
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
