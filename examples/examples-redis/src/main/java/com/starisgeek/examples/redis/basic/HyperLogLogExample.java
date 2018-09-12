package com.starisgeek.examples.redis.basic;

import com.starisgeek.examples.redis.JedisUtil;

import redis.clients.jedis.Jedis;

public class HyperLogLogExample {
	public static void main(String[] args) {
		Jedis client = JedisUtil.buildJedisPool().getResource();
		// pfadd(client);
		pfmerge(client);
		client.close();
	}

	static void pfadd(Jedis client) {
		String key = "hll";
		int count = 10_0000;
		for (int i = 0; i < count; i++) {
			client.pfadd(key, "user" + i);
			// long pfcount = client.pfcount(key);
			// if (pfcount != i + 1) {
			// break;
			// }
		}
		System.out.printf("%d %d", count, client.pfcount(key));
		client.del(key);
	}

	static void pfmerge(Jedis client) {
		String key1 = "h1", key2 = "h2", key3 = "h3";
		int count = 10000;
		for (int i = 0; i < count - 1000; i++) {
			client.pfadd(key1, "user" + i);
		}
		System.out.printf("h1:%d\n", client.pfcount(key1));
		for (int i = 1000; i < count; i++) {
			client.pfadd(key2, "user" + i);
		}
		System.out.printf("h2:%d\n", client.pfcount(key2));
		client.pfmerge(key3, key1, key2);
		System.out.printf("h3:%d", client.pfcount(key3));

		client.del(key1, key2, key3);
	}
}
