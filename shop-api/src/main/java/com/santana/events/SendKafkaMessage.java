package com.santana.events;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.santana.dto.ShopDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SendKafkaMessage {
	
	private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

	private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

	public void sendMessage(ShopDTO msg) {
	    kafkaTemplate.send(SHOP_TOPIC_NAME, msg.getBuyerIdentifier(), msg);
	}

}
