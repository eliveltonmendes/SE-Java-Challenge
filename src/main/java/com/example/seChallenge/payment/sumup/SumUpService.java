package com.example.seChallenge.payment.sumup;

import com.example.seChallenge.domain.Transaction;
import com.example.seChallenge.payment.PaymentService;

public class SumUpService implements PaymentService {

    @Override
    public String processPayment(Transaction transaction) {
        return "http://sumsub.com.br/payment";
    }

    @Override
    public String getType() {
		return "SUM_SUB";
	}
}
