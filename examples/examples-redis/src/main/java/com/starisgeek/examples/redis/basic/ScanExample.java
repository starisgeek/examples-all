package com.starisgeek.examples.redis.basic;

import com.starisgeek.examples.redis.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class ScanExample {
	public static void main(String[] args) {
		Jedis client = JedisUtil.buildJedisPool().getResource();
		for (int i = 0; i < 10000; i++) {
			client.set("key" + i, i + "");
		}
		String string_0 = Integer.toString(0);
		String cursor = string_0;
		ScanParams params = new ScanParams();
		params.count(1000).match("key99*");
		ScanResult<String> result;
		do {
			result = client.scan(cursor, params);
			if (result.getResult() != null && !result.getResult().isEmpty()) {
				for (String s : result.getResult()) {
					System.out.println(s);
				}
				System.out.println("<<<<<<<<<<<<<<<<size=" + result.getResult().size() + ">>>>>>>>>>>>>>>>");
			}
			cursor = result.getStringCursor();
		} while (!result.getStringCursor().equals(string_0));

		client.flushDB();
		client.close();
	}
}
