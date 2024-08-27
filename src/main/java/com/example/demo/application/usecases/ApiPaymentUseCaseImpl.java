package com.example.demo.application.usecases;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exceptions.PaymentException;
import com.example.demo.domain.payment.PaymentGateway;

@Service
@Profile("api")
public class ApiPaymentUseCaseImpl implements PaymentUseCase {

    private final PaymentGateway paymentGateway;

    public ApiPaymentUseCaseImpl(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public void processPayment(String tokenId, String description, long amount, String currency) {
        System.out.println("Processing payment via external API...");

        try {
            paymentGateway.processPayment(tokenId, description, amount, currency);
            System.out.println("Payment successful");
        } catch (PaymentException e) {
            e.printStackTrace();
            System.out.println("Payment failed: " + e.getMessage());
        }
    }
}