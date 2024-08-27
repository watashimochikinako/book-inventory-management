package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.application.usecases.PaymentUseCase;

@Service
public class PaymentService {

    private final PaymentUseCase paymentUseCase;

    public PaymentService(PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    public void processPayment(String tokenId, String description, long amount, String currency) {
        paymentUseCase.processPayment(tokenId, description, amount, currency);
    }
}
