package com.example.demo.presentation.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.application.services.PaymentService;
import com.example.demo.application.services.ProductService;
import com.example.demo.domain.entities.OrderProduct;
import com.example.demo.domain.entities.Payment;
import com.example.demo.domain.entities.Product;
import com.example.demo.presentation.forms.PaymentForm;

import jakarta.validation.Valid;

/**
 * 決済に関連するリクエストを処理するコントローラクラスです。
 */
@Controller
public class PaymentController {

    private final Environment env;
    private final ProductService productService;
    private final PaymentService paymentService;

    public PaymentController(Environment env, ProductService productService, PaymentService paymentService) {
        this.env = env;
        this.productService = productService;
        this.paymentService = paymentService;
    }

    /**
     * 商品選択ページを表示します。
     * 
     * @param model モデルに商品リストを追加
     * @return 商品選択ページのビュー名
     */
    @GetMapping("/product-selection")
    public String showProductSelection(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-selection";
    }

    /**
     * 注文商品の決済処理を行います。
     * プロファイルに応じて、StripeのCheckoutページにリダイレクトするか、決済フォームに遷移します。
     * 
     * @param orderProduct 注文商品エンティティ
     * @param model モデル属性に決済フォームを追加する場合あり
     * @return リダイレクトまたは遷移先のビュー名
     */
    @PostMapping("/process-payment")
    public String processPayment(@ModelAttribute OrderProduct orderProduct, Model model) {
        // 現在のアクティブなプロファイルを取得
        String[] activeProfiles = env.getActiveProfiles();

        if (isApiProfileActive(activeProfiles)) {
            // APIプロファイルの場合、StripeのCheckoutページにリダイレクト
            String checkoutUrl = paymentService.processPayment(orderProduct);
            return "redirect:" + checkoutUrl;
        } else if (isLocalProfileActive(activeProfiles)) {
            // ローカルプロファイルの場合、決済フォームに遷移
            model.addAttribute("paymentForm", new PaymentForm());
            model.addAttribute("orderProduct", orderProduct);
            return "payment-form";
        } else {
            // プロファイルが不明な場合、エラーページに遷移
            return "error";
        }
    }

    /**
     * 決済フォームの送信処理を行います。
     * 
     * @param paymentForm 決済フォームのデータ
     * @param result バリデーション結果
     * @param orderProduct 注文商品エンティティ
     * @return 決済成功後のリダイレクトURL
     */
    @PostMapping("/submit-payment-form")
    public String submitPaymentForm(@ModelAttribute("paymentForm") @Valid PaymentForm paymentForm,
            BindingResult result,
            @ModelAttribute("orderProduct") OrderProduct orderProduct) {

        if (result.hasErrors()) {
            // バリデーションエラーがある場合、決済フォームに戻る
            return "payment-form";
        }

        // 決済を作成し、処理を実行
        Payment payment = paymentService.createPayment(paymentForm, orderProduct);
        paymentService.processPayment(payment);

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

    /**
     * 決済キャンセルページを表示します。
     * 
     * @return 決済キャンセルページのビュー名
     */
    @RequestMapping("/payment-cancel")
    public String paymentCancel() {
        return "payment-cancel";
    }

    /**
     * APIプロファイルがアクティブかどうかを判定します。
     * 
     * @param activeProfiles 現在のアクティブプロファイルの配列
     * @return APIプロファイルがアクティブであれば true、それ以外は false
     */
    private boolean isApiProfileActive(String[] activeProfiles) {
        return Arrays.asList(activeProfiles).contains("api");
    }

    /**
     * ローカルプロファイルがアクティブかどうかを判定します。
     * 
     * @param activeProfiles 現在のアクティブプロファイルの配列
     * @return ローカルプロファイルがアクティブであれば true、それ以外は false
     */
    private boolean isLocalProfileActive(String[] activeProfiles) {
        return Arrays.asList(activeProfiles).contains("local");
    }
}