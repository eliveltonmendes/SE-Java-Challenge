package com.example.seChallenge.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "friend_id")
	private Friend friend;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_link")
    private String paymentLink;

    public Transaction() {}
    public Transaction(Invoice invoice, Friend friend, BigDecimal amount) {
        this.invoice = invoice;
        this.friend = friend;
        this.amount = amount;
    }

    public void addAmount(Double price) {
		amount = amount.add(BigDecimal.valueOf(price));
	}
	
	public void subtractAmount(Double price) {
		amount = amount.subtract(BigDecimal.valueOf(price));
	}
}
