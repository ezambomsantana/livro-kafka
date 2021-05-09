package com.santana.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.santana.model.Shop;

@Getter
@Setter
public class ShopDTO {
    private String identifier;
    private LocalTime dateShop;
    private String status;
    private List<ShopItemDTO> items;

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setIdentifier(shop.getIdentifier());
        shopDTO.setDateShop(shop.getDateShop());
        shopDTO.setStatus(shop.getStatus());
        shopDTO.setItems(shop.getItems().stream().map(i -> ShopItemDTO.convert(i)).collect(Collectors.toList()));
        return shopDTO;
    }

}