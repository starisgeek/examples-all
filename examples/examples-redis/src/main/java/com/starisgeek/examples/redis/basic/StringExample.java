package com.starisgeek.examples.redis.basic;

import com.starisgeek.examples.redis.JedisUtil;

import redis.clients.jedis.Jedis;

/**
 * append会预分配内存，导致内存浪费，并且会形成内存碎片，尽量使用set。
 * @author Administrator
 *
 */
public class StringExample {
	private static final int COUNT = 100_0000;
	private static final String KEY_PREFIX = "key_";
	private static final String FIXD_STRING = fixLengthString(60);

	public static void main(String[] args) {
		Jedis client = JedisUtil.buildJedisPool().getResource();
		/*
		 * # Memory
		 * used_memory:821496
		 * used_memory_human:802.24K
		 * used_memory_rss:2613248
		 * used_memory_rss_human:2.49M
		 * used_memory_peak:822344
		 * used_memory_peak_human:803.07K
		 * total_system_memory:506085376
		 * total_system_memory_human:482.64M
		 * used_memory_lua:37888
		 * used_memory_lua_human:37.00K
		 * maxmemory:0
		 * maxmemory_human:0B
		 * maxmemory_policy:noeviction
		 * mem_fragmentation_ratio:3.18
		 * mem_allocator:jemalloc-4.0.3
		 */

		// firstSet(client);

		/*
		 * used_memory:129208176
		 * used_memory_human:123.22M
		 * used_memory_rss:134238208
		 * used_memory_rss_human:128.02M
		 * used_memory_peak:134340240
		 * used_memory_peak_human:128.12M
		 * total_system_memory:506085376
		 * total_system_memory_human:482.64M
		 * used_memory_lua:37888
		 * used_memory_lua_human:37.00K
		 * maxmemory:0
		 * maxmemory_human:0B
		 * maxmemory_policy:noeviction
		 * mem_fragmentation_ratio:1.04
		 * mem_allocator:jemalloc-4.0.3
		 */

		// secondAppend(client);

		/*
		 * used_memory:321208176
		 * used_memory_human:306.33M
		 * used_memory_rss:330858496
		 * used_memory_rss_human:315.53M
		 * used_memory_peak:323280112
		 * used_memory_peak_human:308.30M
		 * total_system_memory:506085376
		 * total_system_memory_human:482.64M
		 * used_memory_lua:37888
		 * used_memory_lua_human:37.00K
		 * maxmemory:0
		 * maxmemory_human:0B
		 * maxmemory_policy:noeviction
		 * mem_fragmentation_ratio:1.03
		 * mem_allocator:jemalloc-4.0.3
		 */

		// thirdSet(client);

		/*
		 * # Memory
		 * used_memory:129208456
		 * used_memory_human:123.22M
		 * used_memory_rss:148905984
		 * used_memory_rss_human:142.01M
		 * used_memory_peak:323280112
		 * used_memory_peak_human:308.30M
		 * total_system_memory:506085376
		 * total_system_memory_human:482.64M
		 * used_memory_lua:37888
		 * used_memory_lua_human:37.00K
		 * maxmemory:0
		 * maxmemory_human:0B
		 * maxmemory_policy:noeviction
		 * mem_fragmentation_ratio:1.15
		 * mem_allocator:jemalloc-4.0.3
		 */
		client.close();
	}

	static void firstSet(Jedis client) {
		for (int i = 0; i < COUNT; i++) {
			client.set(KEY_PREFIX + i, FIXD_STRING);
		}
	}

	static void secondAppend(Jedis client) {
		for (int i = 0; i < COUNT; i++) {
			client.append(KEY_PREFIX + i, FIXD_STRING);
		}
	}

	static void thirdSet(Jedis client) {
		for (int i = 0; i < COUNT; i++) {
			client.set(KEY_PREFIX + i, FIXD_STRING);
		}
	}

	static String fixLengthString(int len) {
		char[] chars = new char[len];
		for (int i = 0; i < len; i++) {
			chars[i] = 'a';
		}
		return new String(chars);
	}
}
