package com.example.demo.domain.payment;

import com.example.demo.domain.exceptions.PaymentException;

/**
 * 決済処理を行うためのインターフェースです。
 */
public interface PaymentGateway {

    /**
     * 支払い処理を実行します。
     *
     * @param tokenId     支払いソースを表すトークン（例：クレジットカードトークン）
     * @param description 支払いの説明
     * @param amount      課金額
     * @param currency    支払いの通貨
     * @throws PaymentException 支払い処理中にエラーが発生した場合にスローされます
     */
    void processPayment(String tokenId, String description, long amount, String currency) throws PaymentException;
}