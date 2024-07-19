package com.example.demo.presentation.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.application.services.AuthenticationService;
import com.example.demo.presentation.forms.UserLoginForm;

/**
 * 認証に関するコントローラです。
 */
@Controller
public class AuthController {

    private final AuthenticationService authenticationService;

    /**
     * AuthControllerのコンストラクターです。
     *
     * @param authenticationService 認証ユースケースのインスタンス
     */
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * ログインページへの遷移を処理します。
     *
     * @param model モデルオブジェクト
     * @return ログインページのビュー名
     */
    @GetMapping("/toLogin")
    public String toLoginPage(Model model) {
        model.addAttribute("loginForm", new UserLoginForm());
        return "login";
    }

    /**
     * ログイン処理を行います。
     *
     * @param loginForm ログインフォームオブジェクト
     * @param model モデルオブジェクト
     * @return 認証成功時はトップページへリダイレクト、失敗時は再度ログインページを表示
     */
    @PostMapping("/login")
    public String login(UserLoginForm loginForm, Model model) {
        boolean isAuthenticated = authenticationService.authenticate(loginForm.getEmail(), loginForm.getPassword());
        if (isAuthenticated) {
            return "redirect:/top";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
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
