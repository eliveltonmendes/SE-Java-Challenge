package com.example.seChallenge.order.dto;

import java.math.BigDecimal;

public class OrderResponseDTO {
    private String clientName;
    private BigDecimal percentageValue;
    private BigDecimal totalOrderValue;
    private BigDecimal totalPaymentValue;
    private String paymentLink;

    public OrderResponseDTO() {}

    public OrderResponseDTO(String clientName, BigDecimal percentageValue, BigDecimal totalValue) {
        this.clientName = clientName;
        this.percentageValue = percentageValue;
        this.totalOrderValue = totalValue;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    public BigDecimal getTotalOrderValue() {
        return totalOrderValue;
    }

    public void setTotalOrderValue(BigDecimal totalValue) {
        this.totalOrderValue = totalValue;
    }

    public BigDecimal getPercentageValue() {
        return percentageValue;
    }

    public void setPercentageValue(BigDecimal percentageValue) {
        this.percentageValue = percentageValue;
    }

    public BigDecimal getTotalPaymentValue() {
        return totalPaymentValue;
    }

    public void setTotalPaymentValue(BigDecimal totalPaymentValue) {
        this.totalPaymentValue = totalPaymentValue;
    }
}
