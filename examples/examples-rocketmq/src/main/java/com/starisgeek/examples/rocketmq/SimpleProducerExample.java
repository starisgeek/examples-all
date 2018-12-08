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
			for (int i = 0; i < 10; i++) {
				Message msg = new Message(RocketmqUtil.TOPIC_EXAMPLE,
						("message_" + (i + 1)).getBytes(RocketmqUtil.UTF8));
//				RcoketMQ的延时等级为：1s，5s，10s，30s，1m，2m，3m，4m，5m，6m，7m，8m，9m，10m，20m，30m，1h，2h。
//				level=0，表示不延时。level=1，表示 1 级延时，对应延时 1s。level=2 表示 2 级延时，对应5s，以此类推。
				msg.setDelayTimeLevel(3);
				producer.send(msg);
			}
			logger.info("Message producer send end.");
		} catch (Exception e) {
			logger.error("Failed to send message to rocketmq", e);
		}

		context.close();
	}
}
