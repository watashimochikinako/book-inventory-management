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
     * @param product  商品エンティティ
     * @param quantity 商品の個数
     * @return StripeのCheckoutページのURL
     * @throws PaymentException 決済処理中にエラーが発生した場合にスローされます
     */
    String processPayment(OrderProduct orderProduct) throws PaymentException;

    /**
     * ローカルプロファイルの場合、決済情報をDBに保存します。
     *
     * @param payment  決済エンティティ
     * @throws PaymentException 決済処理中にエラーが発生した場合にスローされます
     */
    void processPayment(Payment payment) throws PaymentException;
}