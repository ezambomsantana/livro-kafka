package com.santana.events;

import jakarta.transaction.Transactional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.santana.dto.ShopDTO;
import com.santana.repository.ReportRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {

	private static final String SHOP_TOPIC_EVENT_NAME 
		= "SHOP_TOPIC_EVENT";
	
	private final ReportRepository reportRepository;
	
		@Transactional
		@KafkaListener(
				topics = SHOP_TOPIC_EVENT_NAME, 
				groupId = "group_report")
		public void listenShopTopic(ShopDTO shopDTO) {
			try {
		    log.info("Compra recebida no tópico: {}.",
		    		shopDTO.getIdentifier());	    
		    reportRepository
		    	.incrementShopStatus(shopDTO.getStatus());
			} catch (Exception e) {
				log.error("Erro no processamento da mensagem", e);
			}
		}

}
