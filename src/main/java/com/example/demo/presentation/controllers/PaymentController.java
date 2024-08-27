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

/**
 * 決済に関連するリクエストを処理するコントローラクラスです。
 */
@Controller
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * PaymentControllerのコンストラクターです。
     * 
     * @param paymentService 決済処理を担当するサービスクラス
     */
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 決済フォームを表示します。
     * 
     * @param model モデルオブジェクト
     * @return 決済フォームのビュー名
     */
    @GetMapping("/payment-form")
    public String showPaymentForm(Model model) {
        model.addAttribute("paymentForm", new PaymentForm());
        return "payment-form";
    }

    /**
     * 決済処理を行います。
     * フォームから送信された決済情報を基に、決済サービスを呼び出します。
     * 
     * @param paymentForm 決済フォームのデータ
     * @param result バリデーション結果
     * @param model モデルオブジェクト
     * @return 成功時は決済成功ページへのリダイレクト、エラーがある場合はフォームページの再表示
     */
    @PostMapping("/process-payment")
    public String processPayment(@Validated PaymentForm paymentForm, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            return "payment-form";
        }

        // 決済処理を呼び出す
        paymentService.processPayment(
                paymentForm.getTokenId(),
                paymentForm.getDescription(),
                paymentForm.getAmount(),
                paymentForm.getCurrency()
        );

        return "redirect:/payment-success";
    }

    /**
     * 決済成功ページを表示します。
     * 
     * @return 決済成功ページのビュー名
     */
    @RequestMapping("/payment-success")
    public String paymentSuccess() {
        return "payment-success";
    }
}