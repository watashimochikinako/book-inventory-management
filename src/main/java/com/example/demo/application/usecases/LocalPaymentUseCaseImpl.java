package com.example.demo.application.usecases;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class LocalPaymentUseCaseImpl implements PaymentUseCase{

    @Override
    public void processPayment() {
        System.out.println("Processing payment via local database...");
        // ローカル決済処理のコードをここに追加
    }
}