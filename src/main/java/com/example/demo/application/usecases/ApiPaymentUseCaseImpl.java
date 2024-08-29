package com.example.demo.application.usecases;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exceptions.PaymentException;
import com.example.demo.domain.payments.PaymentGateway;

/**
 * 外部APIを使用した支払い処理に関するユースケースの実装クラスです。
 */
@Service
@Profile("api")
public class ApiPaymentUseCaseImpl implements PaymentUseCase {

    private final PaymentGateway paymentGateway;

    /**
     * ApiPaymentUseCaseImplのコンストラクタです。
     *
     * @param paymentGateway 支払い処理を行うためのPaymentGatewayのインスタンス
     */
    public ApiPaymentUseCaseImpl(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    /**
     * 外部APIを利用して支払い処理を行います。
     *
     * @param tokenId      支払いソースを表すトークン（例：クレジットカードトークン）
     * @param description  支払いの説明
     * @param amount       課金額
     * @param currency     支払いの通貨
     */
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