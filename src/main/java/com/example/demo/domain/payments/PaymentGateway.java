package com.example.demo.domain.payments;

import com.example.demo.domain.entities.OrderProduct;
import com.example.demo.domain.entities.Payment;
import com.example.demo.domain.exceptions.PaymentException;

/**
 * 決済処理を行うためのインターフェースです。
 */
public interface PaymentGateway {

    /**
     * APIプロファイルの場合、支払い処理を実行し、Stripe CheckoutページのURLを返します。
     *
     * @param orderProduct 注文商品エンティティ
     * @return Stripe CheckoutページのURL
     * @throws PaymentException 支払い処理中にエラーが発生した場合
     */
    String processPayment(OrderProduct orderProduct) throws PaymentException;

    /**
     * ローカルプロファイルの場合、支払い処理を実行し、決済情報をデータベースに保存します。
     *
     * @param payment 決済エンティティ
     * @throws PaymentException 支払い処理中にエラーが発生した場合
     */
    void processPayment(Payment payment) throws PaymentException;
}
