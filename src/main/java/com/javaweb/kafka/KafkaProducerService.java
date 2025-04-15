package com.javaweb.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

	public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(String topic, String message) {
		LOGGER.info("Send to topic: "+topic+" with Message send: "+ message);
		kafkaTemplate.send(topic,message);
	}
}
