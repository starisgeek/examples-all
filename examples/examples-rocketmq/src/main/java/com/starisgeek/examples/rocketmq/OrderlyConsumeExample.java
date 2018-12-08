package com.starisgeek.examples.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class OrderlyConsumeExample {
    private static final Logger logger = LoggerFactory.getLogger(OrderlyConsumeExample.class);

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-rocketmq-consumer.xml");
        DefaultMQPushConsumer consumer = context.getBean(DefaultMQPushConsumer.class);
        // consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    logger.info("[{}] Received message:{} from rocketmq broker, queueId:{}",
                            Thread.currentThread().getName(),
                            new String(msg.getBody(), RocketmqUtil.UTF8), msg.getQueueId());
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.in.read();
        context.close();
    }
}
