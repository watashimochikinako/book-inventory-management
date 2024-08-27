package com.example.demo.domain.payment;

import com.example.demo.domain.exceptions.PaymentException;

public interface PaymentGateway {
    void processPayment(String tokenId, String description, long amount, String currency) throws PaymentException;
}