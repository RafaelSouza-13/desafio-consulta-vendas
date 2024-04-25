package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;


public class SellerNameDTO {
    private String name;


    public SellerNameDTO(String name) {
        this.name = name;
    }

    public SellerNameDTO(Seller seller) {
        this.name = seller.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
