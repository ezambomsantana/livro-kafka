package com.santana.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.santana.dto.ShopDTO;
import com.santana.repository.ReportRepository;

@ExtendWith(SpringExtension.class)
public class ReceiveKafkaMessageTests {

	@InjectMocks
	private ReceiveKafkaMessage receiveKafkaMessage;
	
	@Mock
	private ReportRepository reportRepository;
		
	public ShopDTO getShopDTO() {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setStatus("SUCCESS");		
		return shopDTO;
	}
	

	@Test
	public void testProcessShopSuccess() {

		ShopDTO shopDTO = getShopDTO();
		
		receiveKafkaMessage.listenShopTopic(shopDTO);
		
		Mockito
			.verify(reportRepository, Mockito.times(1))
			.incrementShopStatus(shopDTO.getStatus());
				
	}
}
