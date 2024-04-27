package com.devsuperior.dsmeta.dto;

public class SaleReportDTO {
    private String sellerName;
    private Double total;

    public SaleReportDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getName() {
        return sellerName;
    }

    public void setName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
