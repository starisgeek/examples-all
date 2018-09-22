package com.starisgeek.examples.redis.springdata.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

public class SpringDataUseJedisExample {
	private static final Logger logger = LoggerFactory.getLogger(SpringDataUseJedisExample.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-data-jedis.xml");
		StringRedisTemplate srt = context.getBean("stringRedisTemplate", StringRedisTemplate.class);
		String key = "user";
		srt.opsForValue().set(key, "wanghao");
		String value = srt.opsForValue().get(key);
		logger.info("Found value:{} by key:{}", value, key);
		Boolean deleted = srt.delete(key);
		logger.info("Delete key:{} success:{}", key, deleted);
		context.close();
	}
}
