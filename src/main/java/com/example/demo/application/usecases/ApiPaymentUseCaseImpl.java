package com.example.demo.application.usecases;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.OrderProduct;
import com.example.demo.domain.entities.Payment;
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
     * 外部APIを利用して支払い処理を行います。Stripe CheckoutページのURLを返します。
     *
     * @param product  商品エンティティ
     * @param quantity 商品の個数
     * @return StripeのCheckoutページのURL
     */
    @Override
    public String processPayment(OrderProduct orderProduct) {
        System.out.println("Processing payment via external API...");

        try {
            String checkoutUrl = paymentGateway.processPayment(orderProduct);
            System.out.println("Payment initiated successfully");
            return checkoutUrl;
        } catch (PaymentException e) {
            e.printStackTrace();
            System.out.println("Payment failed: " + e.getMessage());
            throw e; // Rethrow exception to be handled by higher-level logic if necessary
        }
    }

    @Override
    public void processPayment(Payment payment) {
        throw new UnsupportedOperationException("This method is not supported in ApiPaymentUseCaseImpl.");
    }
}