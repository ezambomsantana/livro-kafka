package com.santana.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShopDTO {
    private String identifier;
    private String status;
    private String buyerIdentifier; 
}
