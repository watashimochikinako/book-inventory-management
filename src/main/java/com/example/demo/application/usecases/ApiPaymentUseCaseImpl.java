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
     * @param orderProduct 注文商品エンティティ（商品情報とその個数）
     * @return StripeのCheckoutページのURL
     */
    @Override
    public String processPayment(OrderProduct orderProduct) {
        System.out.println("Processing payment via external API...");

        try {
            // PaymentGatewayを通して決済を処理し、CheckoutページのURLを取得
            String checkoutUrl = paymentGateway.processPayment(orderProduct);
            System.out.println("Payment initiated successfully");
            return checkoutUrl;
        } catch (PaymentException e) {
            // 決済処理が失敗した場合の例外処理
            e.printStackTrace();
            System.out.println("Payment failed: " + e.getMessage());
            throw e; // 必要に応じて上位層で例外を処理するために再スロー
        }
    }

    /**
     * このクラスでは、ローカルプロファイルでの支払い処理はサポートされていません。
     * 
     * @param payment 決済情報
     * @throws UnsupportedOperationException このメソッドはサポートされていません
     */
    @Override
    public void processPayment(Payment payment) {
        throw new UnsupportedOperationException("This method is not supported in ApiPaymentUseCaseImpl.");
    }
}
