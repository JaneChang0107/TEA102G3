package com.websocketchat.jedis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

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
        // 設置redis中的key值
		String key = new StringBuilder(sender).append(":").append(receiver).toString();

		// 建立連線(類似con)
		Jedis jedis = null;
		// 連線由連線值中取得
		jedis = pool.getResource();
		// 連線密碼
		jedis.auth("123456");

		// 取得對話訊息字串(key,一開始,最後)
		List<String> historyData = jedis.lrange(key, 0, -1);
		// 取得完關閉
		jedis.close();
		// 回傳歷史訊息
		return historyData;
	}

	public static void saveChatMessage(String sender, String receiver, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		// 設置雙方的redis中k值
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();

		// 以下取得連線
		Jedis jedis = pool.getResource();
		jedis.auth("123456");

		// 儲存資料
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
