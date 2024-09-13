package com.example.demo.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.application.services.PaymentService;
import com.example.demo.domain.entities.OrderProduct;
import com.example.demo.domain.entities.Payment;
import com.example.demo.presentation.forms.PaymentForm;

/**
 * 決済に関連するリクエストを処理するコントローラクラスです。
 */
@Controller
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process-payment")
    public String processPayment(@ModelAttribute OrderProduct orderProduct, Model model) {
        // 環境変数からプロファイルを取得
        String profile = System.getenv("SPRING_PROFILES_ACTIVE");

        if ("api".equals(profile)) {
            // APIプロファイルの場合、StripeのCheckoutページにリダイレクト
            String checkoutUrl = paymentService.processPayment(orderProduct);
            return "redirect:" + checkoutUrl;
        } else if ("local".equals(profile)) {
            // ローカルプロファイルの場合、決済フォームに遷移
            model.addAttribute("orderProduct", orderProduct);
            return "payment-form";
        } else {
            // デフォルトのエラーページ
            return "error";
        }
    }

    @PostMapping("/submit-payment-form")
    public String submitPaymentForm(@Validated PaymentForm paymentForm, OrderProduct orderProduct, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "payment-form";
        }

        Payment payment = new Payment();
        payment.setEmail(paymentForm.getEmail());
        payment.setCardNumber(paymentForm.getCardNumber());
        payment.setExpMonth(paymentForm.getExpMonth());
        payment.setExpYear(paymentForm.getExpYear());
        payment.setCvc(paymentForm.getCvc());
        payment.setCardHolder(paymentForm.getCardHolder());
        payment.setCountry(paymentForm.getCountry());

        // OrderProduct の情報を設定
        payment.setProductId(orderProduct.getProductId());
        payment.setQuantity(orderProduct.getQuantity());

        paymentService.processPayment(payment);

        return "redirect:/payment-success";
    }

    @RequestMapping("/payment-success")
    public String paymentSuccess() {
        return "payment-success";
    }

    @RequestMapping("/payment-cancel")
    public String paymentCancel() {
        return "payment-cancel";
    }
}