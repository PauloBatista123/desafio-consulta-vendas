package com.devsuperior.dsmeta.dto;

import java.math.BigDecimal;

public class SaleSummary {
    private String sellerName;
    private Double total;

    public SaleSummary(Double total, String sellerName) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getSallerName() {
        return sellerName;
    }

    public void setSallerName(String sallerName) {
        this.sellerName = sallerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SaleSummary{" +
                "sallerName='" + sellerName + '\'' +
                ", total=" + total +
                '}';
    }
}
