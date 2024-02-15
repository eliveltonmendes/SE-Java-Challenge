package com.example.seChallenge.payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFactory {
	private static final Map<String, PaymentService> myServiceCache = new HashMap<>();

    private PaymentServiceFactory(List<PaymentService> services) {
        for(PaymentService service : services) {
            myServiceCache.put(service.getType(), service);
        }
    }

    public PaymentService getService(String type) throws Exception {
        PaymentService service = myServiceCache.get(type);
        if(service == null) throw new Exception("Unknown service type: " + type);
        return service;
    }
}