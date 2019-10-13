package com.zbz.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

/**
 * Kafka消费者服务
 * 
 * @ClassName: KafkaConsumerService
 */
public class KafkaConsumerService implements MessageListener<String, String> {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		logger.info("===消费者消费前=====");
		if ("helloTopic".equals(data.topic())) {
			logger.info("消费者使用helloTopic消费信息: " + data.value());
		}
		logger.info("===消费者消费后=====");	
	}

}
