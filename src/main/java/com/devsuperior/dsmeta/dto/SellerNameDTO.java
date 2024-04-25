package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SellerNameDTO {
    private String name;
    private List<SaleMinDTO> sales = new ArrayList<>();

    public SellerNameDTO(String name, List<SaleMinDTO> salesDto) {
        this.name = name;
        this.sales = salesDto;
    }

    public SellerNameDTO(Seller seller) {
        this.name = seller.getName();
        this.sales = seller.getSales().stream().map(x -> new SaleMinDTO(x)).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SaleMinDTO> getSales() {
        return sales;
    }
}
