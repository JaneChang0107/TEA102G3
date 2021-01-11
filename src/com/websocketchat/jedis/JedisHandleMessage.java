package com.websocketchat.jedis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// ���d��key���]�p��(�o�e�̦W��:�����̦W��)�A������ĥ�(�o�e�̷|���s��:�����̷|���s��)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	
	public static Set<String> getAllChatrooms(String sender) {
//		String key = new StringBuilder(sender).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		
		Set<String> chatrooms = jedis.keys(sender+"*");
		Set<String> names = new HashSet<>();
		for (String str : chatrooms) {
			String m_id = str.substring(str.lastIndexOf(":") + 1);
			names.add(m_id);
		}
		jedis.close();
		return names;
	}
	
	
	public static List<String> getHistoryMsg(String sender, String receiver) {
        // �]�mredis����key��
		String key = new StringBuilder(sender).append(":").append(receiver).toString();

		// �إ߳s�u(����con)
		Jedis jedis = null;
		// �s�u�ѳs�u�Ȥ����o
		jedis = pool.getResource();
		// �s�u�K�X
		jedis.auth("123456");

		// ���o��ܰT���r��(key,�@�}�l,�̫�)
		List<String> historyData = jedis.lrange(key, 0, -1);
		// ���o������
		jedis.close();
		// �^�Ǿ��v�T��
		return historyData;
	}

	public static void saveChatMessage(String sender, String receiver, String message) {
		// ������ӻ��A���n�U�s�۾��v��ѰO��
		// �]�m���誺redis��k��
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();

		// �H�U���o�s�u
		Jedis jedis = pool.getResource();
		jedis.auth("123456");

		// �x�s���
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);
		
		String haveChatReceiverKey = "receiver";
		List<String> historyName = jedis.lrange(haveChatReceiverKey, 0, -1);
		for (String name : historyName) {
			if (name.equals(sender)) {
				jedis.close();
				return;
			}
		}
		String haveChatSenderKey = "sender";
		List<String> historyName1 = jedis.lrange(haveChatSenderKey, 0, -1);
		for (String name : historyName1) {
			if (name.equals(receiver)) {
				jedis.close();
				return;
			}
		}
		
		jedis.rpush(haveChatReceiverKey, sender);
		jedis.rpush(haveChatSenderKey, sender);
		
		jedis.close();
	}
}
