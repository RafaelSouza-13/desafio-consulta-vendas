package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

public class SallerNameDTO {
    private String name;

    public SallerNameDTO(String name) {
        this.name = name;
    }

    public SallerNameDTO(Seller seller) {
        this.name = seller.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
