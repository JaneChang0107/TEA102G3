package com.websocketchat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.io.OutputDecorator;
import com.member.model.MemberVO;
import com.websocketchat.jedis.JedisPoolUtil;

import oracle.sql.ArrayDescriptor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleChatroom {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<ChatroomVO> getAllChatrooms(String sender) {
		String key = new StringBuilder(sender).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		Set<String> chatrooms = jedis.keys("*");
		
//		System.out.println(chatrooms.size());
		

//		for (String chatroom : chatrooms) {
////			output.add(chatroom);
////			System.out.println("chatroom" + chatroom);
//		
//		}
		
		//todo:getAllChatroom from database
		//getemails from redis
		//getmembers from oracle by email
		
		
		List<ChatroomVO> output = new ArrayList<>();
		
		MemberVO member1 = new MemberVO();
		member1.setM_id("M00001");
		member1.setM_name("李宵搖");
		
		MemberVO member2 = new MemberVO();
		member2.setM_id("M00002");
		member2.setM_name("李XX");
		
		MemberVO member3 = new MemberVO();
		member3.setM_id("M00003");
		member3.setM_name("林XX");
		
		output.add(new ChatroomVO(member1));
		output.add(new ChatroomVO(member2));
		output.add(new ChatroomVO(member3));
		
		
		
		jedis.close();
		return output;
	}

}
