// package com.example.demo.infrastructure.payments;

// import org.springframework.context.annotation.Profile;
// import org.springframework.stereotype.Component;

// // import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

// import com.example.demo.domain.exceptions.PaymentException;
// import com.example.demo.domain.payments.PaymentGateway;

// /**
//  * ローカルデータベースを使用した支払い処理を行うクラスです。
//  */
// @Component
// @Profile("local")
// public class JdbcPaymentGateway implements PaymentGateway{

//     // private final NamedParameterJdbcTemplate template;

//     /**
//      * JdbcPaymentGatewayのコンストラクターです。
//      *
//      * @param template NamedParameterJdbcTemplateのインスタンス
//      */
//     // public JdbcPaymentGateway(NamedParameterJdbcTemplate template) {
//     //     this.template = template;
//     // }

//     @Override
//     public void processPayment(String tokenId, String description, long amount, String currency) throws PaymentException {
//         // try {
//         //     // ローカルデータベースに支払い情報を挿入する例
//         //     String sql = "INSERT INTO payments (token_id, description, amount, currency) VALUES (?, ?, ?, ?)";
//         //     template.insert(sql, tokenId, description, amount, currency);
//         //     System.out.println("Payment processed successfully.");
//         // } catch (Exception e) {
//         //     throw new PaymentException("Failed to process payment", e);
//         // }
//     }
// }
