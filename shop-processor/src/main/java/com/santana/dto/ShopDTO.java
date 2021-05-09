package com.santana.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ShopDTO {
    private String identifier;
    private LocalTime dateShop;
    private String status;
    private List<ShopItemDTO> items;
}