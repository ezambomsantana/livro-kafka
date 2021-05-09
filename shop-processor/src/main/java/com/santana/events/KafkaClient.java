package com.santana.events;

import com.santana.dto.ShopItemDTO;
import com.santana.dto.ShopStatusDTO;
import com.santana.model.Product;
import com.santana.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.santana.dto.ShopDTO;

import lombok.RequiredArgsConstructor;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaClient {

    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

    private final ProductRepository productRepository;

    @KafkaListener(topics = SHOP_TOPIC_NAME)
    public void listenGroupFoo(ShopDTO shopDTO) {
        log.info("Received Shop: {} " + shopDTO.getIdentifier());

        for (ShopItemDTO item : shopDTO.getItems()) {
            Product product = productRepository.findByIdentifier(item.getProductIdentifier());
            if (product == null) {
                ShopStatusDTO shopStatus = buildShopStatus(shopDTO, "CANCELED", "Product not found.");
            } else if (product.getAmount() < item.getAmount()) {
                ShopStatusDTO shopStatus = buildShopStatus(shopDTO, "CANCELED", "Not enough product.");

            } else {
                ShopStatusDTO shopStatus = buildShopStatus(shopDTO, "CONFIRMED", null);
                product.setAmount(product.getAmount() - item.getAmount());
                productRepository.save(product);
            }
        }

    }

    private ShopStatusDTO buildShopStatus(ShopDTO shopDTO, String status, String reason) {
        return ShopStatusDTO
                .builder()
                .shopIdentifier(shopDTO.getIdentifier())
                .status(status)
                .reason(reason).build();
    }

}