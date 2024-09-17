package com.example.demo.application.usecases;

import com.example.demo.domain.entities.OrderProduct;
import com.example.demo.domain.entities.Payment;
import com.example.demo.domain.exceptions.PaymentException;

/**
 * 支払い処理に関するユースケースのインターフェースです。
 */
public interface PaymentUseCase {

    /**
     * APIプロファイルの場合、Stripe Checkoutページにリダイレクトします。
     *
     * @param orderProduct 注文商品エンティティ（購入する商品とその個数の情報を持つ）
     * @return StripeのCheckoutページのURL
     * @throws PaymentException 決済処理中にエラーが発生した場合にスローされます
     */
    String processPayment(OrderProduct orderProduct) throws PaymentException;

    /**
     * ローカルプロファイルの場合、決済情報をDBに保存します。
     *
     * @param payment 決済エンティティ（クレジットカードや顧客情報などの決済情報を持つ）
     * @throws PaymentException 決済処理中にエラーが発生した場合にスローされます
     */
    void processPayment(Payment payment) throws PaymentException;
}
