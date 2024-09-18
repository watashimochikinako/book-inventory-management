package com.example.demo.infrastructure.payments;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.demo.domain.entities.OrderProduct;
import com.example.demo.domain.entities.Payment;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.demo.domain.exceptions.PaymentException;
import com.example.demo.domain.payments.PaymentGateway;

/**
 * ローカルデータベースを使用した支払い処理を行うクラスです。
 */
@Component
@Profile("local")
public class JdbcPaymentGateway implements PaymentGateway{

    private final NamedParameterJdbcTemplate template;

    /**
     * JdbcPaymentGatewayのコンストラクターです。
     *
     * @param template NamedParameterJdbcTemplateのインスタンス
     */
    public JdbcPaymentGateway(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    /**
     * 支払い情報をローカルデータベースに保存します。
     * 
     * @param payment 支払い情報を含むPaymentオブジェクトです。
     * @throws PaymentException 支払い処理中にエラーが発生した場合にスローされます。
     */
    @Override
    public void processPayment(Payment payment) throws PaymentException {
        try {
            // SQLクエリの作成
            String sql = "INSERT INTO payments (email, card_number, exp_month, exp_year, cvc, card_holder, country, price_id, quantity) " +
                         "VALUES (:email, :cardNumber, :expMonth, :expYear, :cvc, :cardHolder, :country, :priceId, :quantity)";

            // パラメータの設定
            Map<String, Object> params = new HashMap<>();
            params.put("email", payment.getEmail());
            params.put("cardNumber", payment.getCardNumber());
            params.put("expMonth", payment.getExpMonth());
            params.put("expYear", payment.getExpYear());
            params.put("cvc", payment.getCvc());
            params.put("cardHolder", payment.getCardHolder());
            params.put("country", payment.getCountry());
            params.put("priceId", payment.getPriceId());
            params.put("quantity", payment.getQuantity());

            // SQLクエリの実行
            template.update(sql, params);
            System.out.println("Payment processed successfully.");
        } catch (Exception e) {
            throw new PaymentException("Failed to process payment", e);
        }
    }

    /**
     * APIプロファイルの決済処理はサポートされていません。
     * 
     * @param orderProduct 注文商品エンティティ
     * @throws PaymentException サポートされていない場合にスローされます
     */
    @Override
    public String processPayment(OrderProduct orderProduct) throws PaymentException {
        throw new UnsupportedOperationException("API payment processing is not supported in the Local profile.");
    }
}
