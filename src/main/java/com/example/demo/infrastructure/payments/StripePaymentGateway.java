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
     * APIプロファイルの場合、Stripe Checkoutページにリダイレクトします。
     * 
     * @param product  商品エンティティ
     * @param quantity 商品の個数
     * @return StripeのCheckoutページのURL
     * @throws PaymentException 決済処理中にエラーが発生した場合にスローされます
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

            // Create Stripe Checkout Session
            Session session = Session.create(params);
            String checkoutUrl = session.getUrl();

            // リダイレクトURLをログに出力
            System.out.println("Redirect to: " + checkoutUrl);

            // StripeのチェックアウトURLを返す
            return checkoutUrl;

        } catch (StripeException e) {
            throw new PaymentException("Stripe API Error: " + e.getMessage(), e);
        }
    }

    /**
     * ローカルプロファイルでの決済処理はサポートしていません。
     * 
     * @param payment  決済エンティティ
     * @throws PaymentException 決済処理中にエラーが発生した場合にスローされます
     */
    @Override
    public void processPayment(Payment payment) throws PaymentException {
        throw new UnsupportedOperationException("Local payment processing is not supported in the API profile.");
    }
}
