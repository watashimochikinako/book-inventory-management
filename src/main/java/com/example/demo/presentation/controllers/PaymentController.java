package com.example.demo.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.application.services.PaymentService;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process-payment")
    public String processPayment() {
        // 決済処理を呼び出す
        paymentService.processPayment();
        // 決済完了後にリダイレクトするページを指定
        return "redirect:/payment-success";
    }

    @RequestMapping("/payment-success")
    public String paymentSuccess() {
        // 決済成功ページを返す
        return "payment-success";
    }
}
