package com.javaweb.kafka;

import com.javaweb.model.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
public class KafkaConsumerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);

	@KafkaListener(topics = "28Tech", groupId = "28Tech-group")
	public void listen1(UserDTO message) {
		LOGGER.info("Message send -> " + message.getFullName());
	}

	@KafkaListener(topics = "topic-demo", groupId = "28Tech-group")
	public void listen2(String message) {
		LOGGER.info("Message send -> " + message);
	}

	@KafkaListener(topics = "topic-demo2", groupId = "28Tech-group")
	public void listen3(String message) {
		LOGGER.info("Message send -> " + message);
	}
	
	@KafkaListener(topics = "topic-demo2", groupId = "28Tech-group")
	public void listen4(UserDTO message) {
		LOGGER.info("Message send -> " + message);
	}
}
