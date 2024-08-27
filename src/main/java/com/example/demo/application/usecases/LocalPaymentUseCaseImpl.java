package com.example.demo.application.usecases;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * ローカル環境での支払い処理に関するユースケースの実装クラスです。
 */
@Service
@Profile("local")
public class LocalPaymentUseCaseImpl implements PaymentUseCase{

    /**
     * 支払い処理をローカルデータベースで模擬的に行います。
     *
     * @param tokenId      支払いソースを表すトークン（例：クレジットカードトークン）
     * @param description  支払いの説明
     * @param amount       課金額
     * @param currency     支払いの通貨
     */
    @Override
    public void processPayment(String tokenId, String description, long amount, String currency) {
        System.out.println("Processing payment via local database...");
        // ローカル決済処理のコードをここに追加
        System.out.println("Description: " + description);
        System.out.println("Amount: " + amount);
        System.out.println("Currency: " + currency);
    }
}