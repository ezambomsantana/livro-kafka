package com.santana.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.santana.dto.ShopDTO;

@ExtendWith(SpringExtension.class)
public class SendKafkaMessageTests {

	@InjectMocks
	private SendKafkaMessage sendKafkaMessage;
	
	@Mock
	private KafkaTemplate<String, ShopDTO> kafkaTemplate;
	
	private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
	
	@Test
	public void testSendMessage() {
		ShopDTO shopDTO = new ShopDTO();	
		shopDTO.setStatus("SUCCESS");
		shopDTO.setBuyerIdentifier("b-1");
		
		sendKafkaMessage.sendMessage(shopDTO);
		
		Mockito
		.verify(kafkaTemplate, Mockito.times(1))
		.send(SHOP_TOPIC_NAME, "b-1", shopDTO);
	}
}
