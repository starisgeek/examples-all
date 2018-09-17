package com.starisgeek.examples.rocketmq;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchConsumeExample {
	private static final Logger logger = LoggerFactory.getLogger(BatchConsumeExample.class);

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-rocketmq-consumer.xml");
		DefaultMQPushConsumer consumer = context.getBean(DefaultMQPushConsumer.class);
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
					ConsumeConcurrentlyContext context) {
				logger.info("[{}] message's size:{}", Thread.currentThread().getName(),
						msgs.size());
				for (MessageExt msg : msgs) {
					logger.info("[{}] Received message:{} from rocketmq broker",
							Thread.currentThread().getName(),
							new String(msg.getBody(), RocketmqUtil.UTF8));
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();
		System.in.read();
		context.close();
	}
}
