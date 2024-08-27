package com.example.demo.infrastructure.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.domain.exceptions.PaymentException;
import com.example.demo.domain.payment.PaymentGateway;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

/**
 * Stripe を利用して決済処理を行うための実装クラスです。
 * {@link PaymentGateway} インターフェースを実装し、Stripe の API を使用して決済を処理します。
 */
@Component
public class StripePaymentGateway implements PaymentGateway {

    /**
     * StripePaymentGatewayのコンストラクタです。
     * 
     * @param stripeApiKey Stripe の API キー
     */
    public StripePaymentGateway(@Value("${stripe.api.key}") String stripeApiKey) {
        Stripe.apiKey = stripeApiKey;
    }

    /**
     * 指定されたパラメータで決済処理を行います。
     * Stripe の API を使って決済を実行し、決済 ID を出力します。
     * 
     * @param tokenId     決済トークン ID
     * @param description 決済の説明
     * @param amount      決済金額（最小単位で指定、例えばセント）
     * @param currency    通貨コード（例：USD）
     * @throws PaymentException 決済処理中にエラーが発生した場合にスローされます
     */
    @Override
    public void processPayment(String tokenId, String description, long amount, String currency) {

        try {
            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount(amount)
                    .setCurrency(currency)
                    .setDescription(description)
                    .setSource(tokenId)
                    .build();

            Charge charge = Charge.create(params);
            System.out.println("Charge ID: " + charge.getId());

        } catch (StripeException e) {
            throw new PaymentException("Payment failed: " + e.getMessage(), e);
        }
    }
}
