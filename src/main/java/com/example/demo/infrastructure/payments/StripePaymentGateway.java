package com.example.demo.infrastructure.payments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.demo.domain.entities.OrderProduct;
import com.example.demo.domain.entities.Payment;
import com.example.demo.domain.exceptions.PaymentException;
import com.example.demo.domain.payments.PaymentGateway;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.annotation.PostConstruct;

/**
 * Stripe を利用して決済処理を行うための実装クラスです。
 * {@link PaymentGateway} インターフェースを実装し、Stripe の API を使用して決済を処理します。
 */
@Component
@Profile("api")
public class StripePaymentGateway implements PaymentGateway {

    @Value("${app.domain}")
    private String yourDomain;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    /**
     * StripeのAPIキーを初期化します。
     */
    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    /**
     * APIプロファイルでStripe Checkoutページにリダイレクトします。
     * 
     * @param orderProduct 商品エンティティと数量
     * @return Stripe CheckoutページのURL
     * @throws PaymentException 決済処理中にエラーが発生した場合
     */
    @Override
    public String processPayment(OrderProduct orderProduct) throws PaymentException {
        try {
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(yourDomain + "/payment-success")
                    .setCancelUrl(yourDomain + "/payment-cancel")
                    .addLineItem(
                        SessionCreateParams.LineItem.builder()
                            .setPrice(orderProduct.getPriceId())
                            .setQuantity((long) orderProduct.getQuantity())
                            .build())
                    .build();

            // Stripe Checkoutセッションの作成
            Session session = Session.create(params);
            String checkoutUrl = session.getUrl();

            // チェックアウトURLをログに出力
            System.out.println("Redirect to: " + checkoutUrl);

            return checkoutUrl;

        } catch (StripeException e) {
            throw new PaymentException("Stripe API Error: " + e.getMessage(), e);
        }
    }

    /**
     * ローカルプロファイルの決済処理はサポートされていません。
     * 
     * @param payment 決済エンティティ
     * @throws PaymentException サポートされていない場合にスローされます
     */
    @Override
    public void processPayment(Payment payment) throws PaymentException {
        throw new UnsupportedOperationException("Local payment processing is not supported in the API profile.");
    }
}