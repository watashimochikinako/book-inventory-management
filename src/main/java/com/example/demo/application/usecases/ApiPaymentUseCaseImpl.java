package com.example.demo.application.usecases;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("api")
public class ApiPaymentUseCaseImpl implements PaymentUseCase{

    @Override
    public void processPayment() {
        System.out.println("Processing payment via external API...");
        // 外部API決済処理のコードをここに追加
    }
}