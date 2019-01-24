package com.starisgeek.examples.redis.springdata.lettuce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 重要：在连接redis sentinel时，sentinel.conf里要配置protected-mode no
 * @author Administrator
 *
 */
public class LettuceSentinelExample {
	private static Logger logger = LoggerFactory.getLogger(LettuceSentinelExample.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-data-lettuce.xml");
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
