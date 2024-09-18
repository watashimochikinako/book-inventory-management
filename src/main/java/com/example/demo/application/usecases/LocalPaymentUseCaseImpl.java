package com.example.demo.application.usecases;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.OrderProduct;
import com.example.demo.domain.entities.Payment;
import com.example.demo.domain.exceptions.PaymentException;
import com.example.demo.domain.payments.PaymentGateway;

/**
 * ローカル環境での支払い処理に関するユースケースの実装クラスです。
 */
@Service
@Profile("local")
public class LocalPaymentUseCaseImpl implements PaymentUseCase {

    private final PaymentGateway paymentGateway;

    /**
     * ApiPaymentUseCaseImplのコンストラクタです。
     *
     * @param paymentGateway 支払い処理を行うためのPaymentGatewayのインスタンス
     */
    public LocalPaymentUseCaseImpl(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    /**
     * 支払い処理をローカルデータベースで模擬的に行います。
     *
     * @param tokenId     支払いソースを表すトークン（例：クレジットカードトークン）
     * @param description 支払いの説明
     * @param amount      課金額
     * @param currency    支払いの通貨
     */
    @Override
    public void processPayment(Payment payment) {
        System.out.println("Processing payment via local database...");
        paymentGateway.processPayment(payment);
    }

    /**
     * このクラスでは、APIプロファイルでの支払い処理はサポートされていません。
     * 
     * @param orderProduct 注文商品情報
     * @throws UnsupportedOperationException このメソッドはサポートされていません
     */
    @Override
    public String processPayment(OrderProduct orderProduct) throws PaymentException {
        throw new UnsupportedOperationException("This method is not supported in LocalPaymentUseCaseImpl.");
    }
}