package com.starisgeek.examples.redis.lock;

import java.util.concurrent.TimeUnit;

import com.starisgeek.examples.redis.JedisUtil;

import redis.clients.jedis.Jedis;

public class DistributedLock {
	private final Jedis client;
	private static final String DEFAULT_LOCK_KEY = "LOCK_KEY";
	private static final String DEFAULT_LOCK_VALUE = "LV";
	private static final int MAX_LOCK_TIMEOUT = 10;

	public DistributedLock(Jedis client) {
		this.client = client;
	}

	public void lock() {
		while (client.set(DEFAULT_LOCK_KEY, DEFAULT_LOCK_VALUE, "nx", "ex", MAX_LOCK_TIMEOUT) == null) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}

	public boolean tryLock() {
		return client.set(DEFAULT_LOCK_KEY, DEFAULT_LOCK_VALUE, "nx", "ex", MAX_LOCK_TIMEOUT) != null;
	}

	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		long start = System.currentTimeMillis();
		long timeout = unit.toMillis(time);
		String result = null;
		do {
			result = client.set(DEFAULT_LOCK_KEY, DEFAULT_LOCK_VALUE, "nx", "ex", MAX_LOCK_TIMEOUT);
			if (result != null) {
				return true;
			}
			TimeUnit.MILLISECONDS.sleep(50);
		} while (System.currentTimeMillis() - start < timeout);
		return false;
	}

	public void unlock() {
		client.del(DEFAULT_LOCK_KEY);
	}

	public static void main(String[] args) throws Exception {
		Jedis client = JedisUtil.buildJedisPool().getResource();
		DistributedLock lock = new DistributedLock(client);
		lock.lock();
		System.out.println(lock.tryLock(5, TimeUnit.SECONDS));
		lock.unlock();
		client.close();
	}
}
