package com.runlight.model;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RunlightJedis {

	
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	public static List<String> getHistory(String key, String value) {
		String indexkey = new StringBuilder(key).append(":").append(value).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		List<String> historyData = jedis.lrange(indexkey, 0, -1);
		jedis.close();
		return historyData;
	}

	
	
}
