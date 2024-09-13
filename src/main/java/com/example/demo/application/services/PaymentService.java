package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.application.usecases.PaymentUseCase;
import com.example.demo.domain.entities.OrderProduct;
import com.example.demo.domain.entities.Payment;

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
     * 支払い処理を実行します。APIプロファイルの場合、Stripe CheckoutページのURLを返します。
     *
     * @param orderProduct  注文商品エンティティ
     * @return StripeのCheckoutページのURL
     */
    public String processPayment(OrderProduct orderProduct) {
        return paymentUseCase.processPayment(orderProduct);
    }

    /**
     * 支払い処理を実行します。ローカルプロファイルの場合、決済情報をDBに保存します。
     *
     * @param payment 決済エンティティ
     */
    public void processPayment(Payment payment) {
        paymentUseCase.processPayment(payment);
    }
}