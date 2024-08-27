package com.example.demo.application.usecases;

/**
 * 支払い処理に関するユースケースのインターフェースです。
 */
public interface PaymentUseCase {

    /**
     * 支払い処理を行います。
     *
     * @param tokenId      支払いソースを表すトークン（例：クレジットカードトークン）
     * @param description  支払いの説明
     * @param amount       課金額
     * @param currency     支払いの通貨
     */
    void processPayment(String tokenId, String description, long amount, String currency);
}
