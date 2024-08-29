package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.application.usecases.PaymentUseCase;

/**
 * 支払い処理に関連するサービスクラスです。
 * 
 * このクラスは、ビジネスロジック層の一部であり、ユースケース層の
 * 支払い処理ロジックを利用して、コントローラからのリクエストを処理します。
 */
@Service
public class PaymentService {

    private final PaymentUseCase paymentUseCase;

    /**
     * PaymentServiceのコンストラクタです。
     *
     * @param paymentUseCase 支払い処理を担当するユースケースのインスタンス
     */
    public PaymentService(PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    /**
     * 支払い処理を実行します。
     *
     * @param tokenId     支払いソースを表すトークン（例：クレジットカードトークン）
     * @param description 支払いの説明
     * @param amount      課金額
     * @param currency    支払いの通貨
     */
    public void processPayment(String tokenId, String description, long amount, String currency) {
        paymentUseCase.processPayment(tokenId, description, amount, currency);
    }
}
