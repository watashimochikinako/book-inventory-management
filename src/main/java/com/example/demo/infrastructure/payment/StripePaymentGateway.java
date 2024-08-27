package com.example.demo.infrastructure.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.domain.exceptions.PaymentException;
import com.example.demo.domain.payment.PaymentGateway;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

@Component
public class StripePaymentGateway implements PaymentGateway {

    public StripePaymentGateway(@Value("${stripe.api.key}") String stripeApiKey) {
        Stripe.apiKey = stripeApiKey;
    }

    @Override
    public void processPayment(String tokenId, String description, long amount, String currency) {

        try {
            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount(amount)
                    .setCurrency(currency)
                    .setDescription(description)
                    .setSource(tokenId)
                    .build();

            Charge charge = Charge.create(params);
            System.out.println("Charge ID: " + charge.getId());

        } catch (StripeException e) {
            throw new PaymentException("Payment failed: " + e.getMessage(), e);
        }
    }
}
