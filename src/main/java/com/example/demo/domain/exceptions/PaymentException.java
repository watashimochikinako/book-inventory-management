package com.example.demo.domain.exceptions;

// 支払い処理中の例外を表すカスタム例外クラスです。
public class PaymentException extends RuntimeException {
    
    // エラーメッセージのみを受け取るコンストラクタ
    public PaymentException(String message) {
        super(message);
    }

    // エラーメッセージと原因となる例外を受け取るコンストラクタ
    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}