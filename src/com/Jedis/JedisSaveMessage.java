package com.Jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisSaveMessage {

	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	public static void saveBellMessage(String userid, String message) {
		
		String userBell = new StringBuilder("BellMessage").append(":").append(userid).toString();
		
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		
		jedis.rpush(userBell, message);
		jedis.close();
		
	}
}
