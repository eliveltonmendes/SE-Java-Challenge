package com.example.seChallenge.invoice.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.seChallenge.domain.Friend;

public class OrderResponseDTO {
    private String clientName;
    private Friend friend;
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

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public void calculateDiscount(BigDecimal discount) {
        BigDecimal totalDiscount = this.percentageValue.divide(new BigDecimal(100)).multiply(discount);
        this.totalPaymentValue = this.totalPaymentValue.subtract(totalDiscount).setScale(2, RoundingMode.HALF_UP);
    }

    public void calculateFee(BigDecimal feeValue, long feePercentage) {
        BigDecimal fairFee = this.percentageValue.divide(new BigDecimal(100)).multiply(feeValue);
        BigDecimal fairPercentageFee = this.totalOrderValue.multiply(BigDecimal.valueOf(feePercentage / 100.0));
    
        this.totalPaymentValue = this.totalPaymentValue.add(fairFee).add(fairPercentageFee).setScale(2, RoundingMode.HALF_UP);
    }
}
