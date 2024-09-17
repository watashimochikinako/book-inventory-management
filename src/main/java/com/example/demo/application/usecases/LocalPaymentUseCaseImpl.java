package com.example.demo.application.usecases;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.OrderProduct;
import com.example.demo.domain.entities.Payment;
import com.example.demo.domain.exceptions.PaymentException;

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
    public void processPayment(Payment payment) {
        System.out.println("Processing payment via local database...");
        // ローカル決済処理のコードをここに追加
    }

    @Override
    public String processPayment(OrderProduct orderProduct) throws PaymentException {
        throw new UnsupportedOperationException("Unimplemented method 'processPayment'");
    }
}