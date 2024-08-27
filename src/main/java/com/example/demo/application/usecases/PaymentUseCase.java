package com.example.demo.application.usecases;

public interface PaymentUseCase {
    void processPayment(String tokenId, String description, long amount, String currency);
}
