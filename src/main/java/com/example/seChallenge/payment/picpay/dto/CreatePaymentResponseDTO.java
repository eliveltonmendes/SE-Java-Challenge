package com.example.seChallenge.payment.picpay.dto;

public class CreatePaymentResponseDTO {
    private String referenceId;
    private String paymentUrl;

    public CreatePaymentResponseDTO() {}
    
    public CreatePaymentResponseDTO(String referenceId, String paymentUrl) {
        this.referenceId = referenceId;
        this.paymentUrl = paymentUrl;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }
}
