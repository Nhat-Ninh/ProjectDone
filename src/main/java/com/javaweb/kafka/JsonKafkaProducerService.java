package com.javaweb.kafka;

import com.javaweb.model.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;



@Service
public class JsonKafkaProducerService {
	private KafkaTemplate<String, String> kafkaTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducerService.class);

	public JsonKafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

//	public void sendMessageForUser(String topic,UserDTO user) {
////		LOGGER.info("Message send " + user.toString());
////		Message<UserDTO> message = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC, topic).build();
////		kafkaTemplate.send(message);
//		LOGGER.debug("Send data user: ");
//		kafkaTemplate.send(topic,user);
//	}

	public void sendMessageForBuilding(String topic, String messages) {
		LOGGER.debug("Send data user: ");
		kafkaTemplate.send(topic, messages);
	}
	public void sendMessageForUser(String topic, String messages) {
		LOGGER.debug("Send data user: ");
		kafkaTemplate.send(topic, messages);
	}
	public void sendMessageForCustomer(String topic, String messages) {
		LOGGER.debug("Send data user: ");
		kafkaTemplate.send(topic, messages);
	}
	public void sendMessageForContact(String topic, String messages) {
		LOGGER.debug("Send data user: ");
		kafkaTemplate.send(topic, messages);
	}

}
