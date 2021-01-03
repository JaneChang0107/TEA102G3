package com.websocket;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.member.model.MemberService;
import com.member.model.MemberVO;


@ServerEndpoint(value = "/WebSocket", configurator = ServletSessionConfig.class)
public class WebSocket {

	public static Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<>());
	HttpSession httpSession;
	String userId;
	
	@OnOpen
	public void onOpen(Session wsSession, EndpointConfig config) {
		httpSession = (HttpSession) config.getUserProperties().get("httpSession");
		userId = (String)httpSession.getAttribute("loginId");
		
		System.out.println(userId + " in");
		
		if(userId != null) {
			sessions.put(userId, wsSession);
		}
	}
	
	@OnMessage
	public void onMessage(String message) {
		
		System.out.println(message);
		
		sessions.forEach((k, v) -> {
			if(v != null && k != userId) {
				v.getAsyncRemote().sendText(message);
			}
		});
		
	}
	
	@OnClose
	public void onClose() {
		
		System.out.println(userId + " out");
		
		if(userId != null) {
			sessions.put(userId, null);
		}
	}
	
	public void userList() {
		MemberService mService = new MemberService();
		List<MemberVO> mVOs = mService.getAll();
		
		mVOs.forEach((mVO) -> {
			sessions.put(mVO.getM_id(), null);
		});
	}
	
	public void updateUserList(String mid) {
		sessions.put(mid, null);
	}
	
}
