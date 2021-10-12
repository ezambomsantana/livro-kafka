package com.santana.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.santana.dto.ShopDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity(name="shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String identifier;
    
    private String status;
    
    @Column(name = "date_shop")
    private LocalDate dateShop;

    @Column(name = "buyer_identifier")
    private String buyerIdentifier;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shop")
    private List<ShopItem> items;

    public static Shop convert(ShopDTO shopDTO) {
    	Shop shop = new Shop();
    	shop.setIdentifier(shopDTO.getIdentifier());
    	shop.setStatus(shopDTO.getStatus());
    	shop.setDateShop(shopDTO.getDateShop());
    	shop.setItems(shopDTO
    			.getItems()
    			.stream()
    			.map(i -> ShopItem.convert(i))
    			.collect(Collectors.toList()));
    	shop.setBuyerIdentifier(shopDTO.getBuyerIdentifier());
    	return shop;
    }

}
