package com.example.seChallenge.order.dto;

import java.math.BigDecimal;

public class OrderDTO {
    private OrderDetailDTO[] details;
    private BigDecimal discountValue;
    private BigDecimal deliveryValue;

    public OrderDTO() {}

    public OrderDTO(OrderDetailDTO[] details, BigDecimal discountValue, BigDecimal deliveryValue) {
        this.details = details;
        this.discountValue = discountValue;
        this.deliveryValue = deliveryValue;
    }

    public OrderDetailDTO[] getDetails() {
        return details;
    }

    public void setDetails(OrderDetailDTO[] details) {
        this.details = details;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public BigDecimal getDeliveryValue() {
        return deliveryValue;
    }

    public void setDeliveryValue(BigDecimal deliveryValue) {
        this.deliveryValue = deliveryValue;
    }

    public BigDecimal getTotalOrderValueWithoutDiscountAndDelivery() {
        BigDecimal totalOrderValue = new BigDecimal(0);
        for (OrderDetailDTO detail: details) {
            totalOrderValue = totalOrderValue.add(detail.getProductValue());
        }
        return totalOrderValue;
    }
}
