package com.starisgeek.examples.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
	private static final int DEFAULT_MAX_POOL_SIZE = 100;
	private static final String HOST = "192.168.0.110";
	private static final int DEFAULT_PORT = 6379;

	public static JedisPool buildJedisPool() {
		return buildJedisPool(DEFAULT_MAX_POOL_SIZE, true);
	}

	public static JedisPool buildJedisPool(int maxTotal, boolean blockWhenExhausted) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(DEFAULT_MAX_POOL_SIZE);
		config.setMinIdle(DEFAULT_MAX_POOL_SIZE);
		config.setBlockWhenExhausted(blockWhenExhausted);
		config.setMaxWaitMillis(1000);
		return new JedisPool(config, HOST, DEFAULT_PORT, 10000);
	}
}
