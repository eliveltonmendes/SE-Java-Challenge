package com.example.seChallenge.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long Id;

    @Column(name = "friend_id", nullable = false)
    private long friendId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private double price;

    public InvoiceDetail() {};
    public InvoiceDetail(long friendId, Invoice invoice, String productName, double price) {
        this.friendId = friendId;
        this.invoice = invoice;
        this.productName = productName;
        this.price = price;
    }
}
