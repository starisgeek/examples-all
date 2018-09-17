package com.starisgeek.examples.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleProducerExample {
	private static final Logger logger = LoggerFactory.getLogger(SimpleProducerExample.class);

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-rocketmq-producer.xml");
		DefaultMQProducer producer = context.getBean(DefaultMQProducer.class);
		try {
			for (int i = 0; i < 100; i++) {
				Message msg = new Message(RocketmqUtil.TOPIC_EXAMPLE,
						("message_" + (i + 1)).getBytes(RocketmqUtil.UTF8));
				producer.send(msg);
			}
			logger.info("Message producer send end.");
		} catch (Exception e) {
			logger.error("Failed to send message to rocketmq", e);
		}

		context.close();
	}
}
