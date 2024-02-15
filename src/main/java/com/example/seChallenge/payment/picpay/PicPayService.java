package com.example.seChallenge.payment.picpay;

import org.springframework.stereotype.Service;

import com.example.seChallenge.domain.Transaction;
import com.example.seChallenge.payment.PaymentService;

@Service
public class PicPayService implements PaymentService {
    String apiUrl = "https://appws.picpay.com/ecommerce/public";

    @Override
    public String processPayment(Transaction transaction) {
        return "http://picpay.com.br/payment";
    }

    @Override
    public String getType() {
		return "PIC_PAY";
	}
}
