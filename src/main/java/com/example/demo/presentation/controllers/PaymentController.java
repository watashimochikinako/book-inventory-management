package com.example.demo.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.application.services.PaymentService;
import com.example.demo.presentation.forms.PaymentForm;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment-form")
    public String showPaymentForm(Model model) {
        model.addAttribute("paymentForm", new PaymentForm());
        return "payment-form";
    }

    @PostMapping("/process-payment")
    public String processPayment(@Validated PaymentForm paymentForm, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            // バリデーションエラーがある場合、フォームに戻す
            return "payment-form";
        }

        // 決済処理を呼び出す
        paymentService.processPayment(
                paymentForm.getTokenId(),
                paymentForm.getDescription(),
                paymentForm.getAmount(),
                paymentForm.getCurrency()
        );

        // 決済完了後にリダイレクトするページを指定
        return "redirect:/payment-success";
    }

    @RequestMapping("/payment-success")
    public String paymentSuccess() {
        // 決済成功ページを返す
        return "payment-success";
    }
}
