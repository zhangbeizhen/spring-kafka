package com.zbz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import com.zbz.utils.CommonUtils;

/**
 * Kafka生产者服务
 * @ClassName: KafkaProducerService
 */
@Service
public class KafkaProducerService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
	public static <K, T> void sendMessage(String topic, Integer partition, Long timestamp, K key, T data) {
		
		KafkaTemplate<K, T> kafkaTemplate = (KafkaTemplate<K, T>) CommonUtils.getBean("kafkaTemplate");
		ListenableFuture<SendResult<K, T>> listenableFuture = null;
		if (kafkaTemplate.getDefaultTopic().equals(topic)) {
			listenableFuture = kafkaTemplate.sendDefault(partition, timestamp, key, data);
		} else {
			listenableFuture = kafkaTemplate.send(topic, partition, timestamp, key, data);
		}

		/**发送成功回调*/
		SuccessCallback<SendResult<K, T>> successCallback = new SuccessCallback<SendResult<K, T>>() {
			@Override
			public void onSuccess(SendResult<K, T> result) {
				// 成功业务逻辑
				logger.info("生产者生产成功.");
			}
		};
		/**发送失败回调*/ 
		FailureCallback failureCallback = new FailureCallback() {
			@Override
			public void onFailure(Throwable e) {
				/**异常处理*/
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		};
		listenableFuture.addCallback(successCallback, failureCallback);
	}

}
