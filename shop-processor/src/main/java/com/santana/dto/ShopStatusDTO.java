package com.santana.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShopStatusDTO {
    private String shopIdentifier;
    private String status;
    private String reason;
}
