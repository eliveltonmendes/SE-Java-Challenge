package com.example.seChallenge.payment;

import com.example.seChallenge.domain.Transaction;

public interface PaymentService {
    String processPayment(Transaction transaction);
    
    String getType();
}
