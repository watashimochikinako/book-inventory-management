package com.example.demo.application.usecases;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

@Service
@Profile("api")
public class ApiPaymentUseCaseImpl implements PaymentUseCase {

    public ApiPaymentUseCaseImpl(@Value("${stripe.api.key}") String stripeApiKey) {
        Stripe.apiKey = stripeApiKey;
    }

    @Override
    public void processPayment(String tokenId, String description, long amount, String currency) {
        System.out.println("Processing payment via external API...");
        
        try {
            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount(amount)
                    .setCurrency(currency)
                    .setDescription(description)
                    .setSource(tokenId)
                    .build();

            Charge charge = Charge.create(params);
            System.out.println("Payment successful: " + charge.getId());
        } catch (StripeException e) {
            e.printStackTrace();
            System.out.println("Payment failed: " + e.getMessage());
        }
    }
}