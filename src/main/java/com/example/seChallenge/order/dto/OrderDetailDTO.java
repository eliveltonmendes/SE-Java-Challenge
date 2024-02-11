package com.example.seChallenge.order.dto;

import java.math.BigDecimal;

public class OrderDetailDTO {
    private String clientName;
    private String productName;
    private BigDecimal productValue;

    public OrderDetailDTO() {}

    public OrderDetailDTO(String clientName, String productName, BigDecimal productValue) {
        this.clientName = clientName;
        this.productName = productName;
        this.productValue = productValue;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductValue() {
        return productValue;
    }

    public void setProductValue(BigDecimal productValue) {
        this.productValue = productValue;
    }
}
