package com.example.demo.domain.payments;

import com.example.demo.domain.entities.OrderProduct;
import com.example.demo.domain.entities.Payment;
import com.example.demo.domain.exceptions.PaymentException;

/**
 * 決済処理を行うためのインターフェースです。
 */
public interface PaymentGateway {

    /**
     * 支払い処理を実行します。APIプロファイルの場合、Stripe Checkoutページにリダイレクトします。
     *
     * @param product  商品エンティティ
     * @param quantity 商品の個数
     * @return StripeのCheckoutページのURL
     * @throws PaymentException 決済処理中にエラーが発生した場合にスローされます
     */
    String processPayment(OrderProduct orderProduct) throws PaymentException;

    /**
     * 支払い処理を実行します。ローカルプロファイルの場合、決済情報をDBに保存します。
     *
     * @param payment 決済エンティティ
     * @throws PaymentException 決済処理中にエラーが発生した場合にスローされます
     */
    void processPayment(Payment payment) throws PaymentException;
}