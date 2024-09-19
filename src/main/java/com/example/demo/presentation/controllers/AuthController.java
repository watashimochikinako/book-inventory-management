package com.example.demo.presentation.controllers;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.application.services.AuthService;

/**
 * 認証に関するコントローラです。
 */
@Controller
public class AuthController {

    private final MessageSource messageSource;
    private final AuthService authService;

    /**
     * AuthControllerのコンストラクタ。
     *
     * @param messageSource メッセージソース (ロケールに応じたメッセージ取得)
     * @param authService 認証関連のビジネスロジックを処理するサービス
     */
    public AuthController(MessageSource messageSource, AuthService authService) {
        this.messageSource = messageSource;
        this.authService = authService;
    }

    /**
     * ログインページへの遷移を処理します。
     *
     * @param model モデルオブジェクト (ビューへ渡すデータ)
     * @param error エラーメッセージ用のリクエストパラメータ (nullの場合もあり)
     * @param locale ユーザーのロケール情報 (エラーメッセージのロケールに使用)
     * @return ログインページのビュー名
     */
    @GetMapping("/toLogin")
    public String toLoginPage(Model model, @RequestParam(required = false) String error, Locale locale) {
        if (error != null) {
            String errorMessage = messageSource.getMessage("login.error.invalidCredentials", null, locale);
            model.addAttribute("errorMessage", errorMessage);
        }
        return "login";
    }

    /**
     * トップページへの遷移を処理します。
     *
     * @param model モデルオブジェクト (ビューへ渡すデータ)
     * @param authentication 認証オブジェクト (現在のユーザーの認証情報)
     * @return トップページのビュー名
     */
    @GetMapping("/top")
    public String topPage(Model model, Authentication authentication) {
        String username = authService.getAuthenticatedUserName(authentication);
        if (username != null) {
            model.addAttribute("loggedIn", true);
            model.addAttribute("username", username);
        } else {
            model.addAttribute("loggedIn", false);
        }
        return "top";
    }
}
