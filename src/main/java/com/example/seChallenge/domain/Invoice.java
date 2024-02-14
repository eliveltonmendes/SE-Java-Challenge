package com.example.seChallenge.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoice_id", unique = true, nullable = false)
    private long invoiceId;

    @Column(name = "discount", nullable = false)
    private Double discount;

    @Column(name = "delivery_fee", nullable = false)
    private Double deliveryFee;

    @Column(name = "percentage_fee", nullable = false)
    private long percentageFee;

    @OneToMany(mappedBy = "invoice",
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<InvoiceDetail> invoiceDetails ;
}
