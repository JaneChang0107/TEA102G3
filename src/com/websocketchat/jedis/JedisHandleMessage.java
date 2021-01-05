package com.websocketchat.jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.io.OutputDecorator;

import oracle.sql.ArrayDescriptor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// ���d��key���]�p��(�o�e�̦W��:�����̦W��)�A������ĥ�(�o�e�̷|���s��:�����̷|���s��)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg(String sender, String receiver) {
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		List<String> historyData = jedis.lrange(key, 0, -1);
//		for (String history : historyData) {
//			System.out.println("hhhh " + history);
//		}
		jedis.close();
		return historyData;
	}

	public static void saveChatMessage(String sender, String receiver, String message) {
		// ������ӻ��A���n�U�s�۾��v��ѰO��
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);
//		System.out.println("HELLOJEDIS");
		jedis.close();
	}

}
