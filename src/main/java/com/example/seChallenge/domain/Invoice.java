package com.example.seChallenge.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "delivery_fee", nullable = false)
    private Double deliveryFee;

    @Column(name = "percentage_fee", nullable = false)
    private long percentageFee;

    // @Column(name = "total_value")
    // private Double totalValue;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    private List<InvoiceDetail> invoiceDetails;

    public BigDecimal getTotalOrderValueWithoutDiscountAndDelivery() {
        BigDecimal totalOrderValue = new BigDecimal(0);
        for (InvoiceDetail detail: invoiceDetails) {
            totalOrderValue = totalOrderValue.add(new BigDecimal(detail.getPrice()));
        }
        return totalOrderValue;
    }

    public BigDecimal getTotalOrderByFriendId(long friendId) {

        System.out.println("filtering by friend " + friendId);
        BigDecimal totalOrderValue = new BigDecimal(0);
        for (InvoiceDetail detail: invoiceDetails) {
            System.out.println("filtering by friend " + friendId + " to " + detail.getFriendId());
            if (detail.getFriendId() != friendId) continue;

            System.out.println("Entered");
            totalOrderValue = totalOrderValue.add(new BigDecimal(detail.getPrice())).setScale(2, RoundingMode.HALF_UP);
        }
        return totalOrderValue;
    }
}
