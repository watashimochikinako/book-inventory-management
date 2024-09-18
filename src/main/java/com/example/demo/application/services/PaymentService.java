package com.example.demo.application.services;

import org.springframework.stereotype.Service;

import com.example.demo.application.usecases.PaymentUseCase;
import com.example.demo.domain.entities.OrderProduct;
import com.example.demo.domain.entities.Payment;
import com.example.demo.presentation.forms.PaymentForm;

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
     * Paymentオブジェクトの作成と初期化
     *
     * @param paymentForm フォームからの入力データ
     * @param orderProduct 注文商品情報
     * @return 初期化されたPaymentオブジェクト
     */
    public Payment createPayment(PaymentForm paymentForm, OrderProduct orderProduct) {
        Payment payment = new Payment();
        payment.setEmail(paymentForm.getEmail());
        payment.setCardNumber(paymentForm.getCardNumber());
        payment.setExpMonth(paymentForm.getExpMonth());
        payment.setExpYear(paymentForm.getExpYear());
        payment.setCvc(paymentForm.getCvc());
        payment.setCardHolder(paymentForm.getCardHolder());
        payment.setCountry(paymentForm.getCountry());
        payment.setPriceId(orderProduct.getPriceId());
        payment.setQuantity(orderProduct.getQuantity());
        return payment;
    }

    /**
     * 注文商品に対する支払い処理を実行します。APIプロファイルの場合、Stripe CheckoutページのURLを返します。
     *
     * @param orderProduct 注文商品エンティティ（購入する商品とその個数の情報を持つ）
     * @return StripeのCheckoutページのURL（成功した場合）
     */
    public String processPayment(OrderProduct orderProduct) {
        return paymentUseCase.processPayment(orderProduct);
    }

    /**
     * ローカルプロファイルの場合、決済処理を実行します。決済情報をローカルデータベースに保存します。
     *
     * @param payment 決済エンティティ（クレジットカードや顧客情報などの決済情報を持つ）
     */
    public void processPayment(Payment payment) {
        paymentUseCase.processPayment(payment);
    }
}
