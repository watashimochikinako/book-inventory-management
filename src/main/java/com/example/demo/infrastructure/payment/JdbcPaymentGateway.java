package com.example.demo.infrastructure.payment;

// import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.demo.domain.exceptions.PaymentException;
import com.example.demo.domain.payment.PaymentGateway;

public class JdbcPaymentGateway implements PaymentGateway{

    // private final NamedParameterJdbcTemplate template;

    /**
     * JdbcPaymentGatewayのコンストラクターです。
     *
     * @param template NamedParameterJdbcTemplateのインスタンス
     */
    // public JdbcPaymentGateway(NamedParameterJdbcTemplate template) {
    //     this.template = template;
    // }

    @Override
    public void processPayment(String tokenId, String description, long amount, String currency) throws PaymentException {
        // try {
        //     // ローカルデータベースに支払い情報を挿入する例
        //     String sql = "INSERT INTO payments (token_id, description, amount, currency) VALUES (?, ?, ?, ?)";
        //     template.insert(sql, tokenId, description, amount, currency);
        //     System.out.println("Payment processed successfully.");
        // } catch (Exception e) {
        //     throw new PaymentException("Failed to process payment", e);
        // }
    }
}
