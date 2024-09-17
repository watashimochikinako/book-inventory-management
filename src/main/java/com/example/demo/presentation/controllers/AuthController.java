package com.example.demo.presentation.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 認証に関するコントローラです。
 */
@Controller
public class AuthController {

    /**
     * ログインページへの遷移を処理します。
     *
     * @param model モデルオブジェクト
     * @param error エラーメッセージ用のパラメータ
     * @return ログインページのビュー名
     */
    @GetMapping("/toLogin")
    public String toLoginPage(Model model, @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
        }
        return "login";
    }

    /**
     * トップページへの遷移を処理します。
     *
     * @param model モデルオブジェクト
     * @param authentication 認証オブジェクト
     * @return トップページのビュー名
     */
    @GetMapping("/top")
    public String topPage(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // ログインユーザーの名前を取得
            model.addAttribute("loggedIn", true);
            model.addAttribute("username", username);
        } else {
            model.addAttribute("loggedIn", false);
        }
        return "top";
    }
}
