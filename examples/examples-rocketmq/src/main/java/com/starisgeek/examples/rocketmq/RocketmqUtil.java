package com.starisgeek.examples.rocketmq;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class RocketmqUtil {
	public static final Charset UTF8 = Charset.forName("UTF-8");
	public static final String TOPIC_EXAMPLE = "ExampleTopic";

	public static void sleep(long timeout, TimeUnit tu) {
		try {
			tu.sleep(timeout);
		} catch (InterruptedException e) {
		}
	}
}
