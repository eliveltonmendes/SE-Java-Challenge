package com.example.seChallenge.domain;

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

    @Column(name = "friend_id", nullable = true)
    private String friendId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")
    private Invoice invoice;
}
