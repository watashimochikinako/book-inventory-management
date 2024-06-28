package com.example.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.usecases.AuthenticationUseCase;
import com.example.demo.presentation.forms.UserLoginForm;

@Controller
public class AuthController {

    private final AuthenticationUseCase authenticationUseCase;

    /**
     * AuthControllerのコンストラクターです。
     *
     * @param authenticationUseCase 認証ユースケースのインスタンス
     */
    public AuthController(AuthenticationUseCase authenticationUseCase) {
        this.authenticationUseCase = authenticationUseCase;
    }

    @ModelAttribute
    public UserLoginForm setUsetLoginForm() {
        return new UserLoginForm();
    }

    @GetMapping("/toLogin")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new UserLoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(UserLoginForm loginForm, Model model) {
        boolean isAuthenticated = authenticationUseCase.authenticate(loginForm.getEmail(), loginForm.getPassword());
        if (isAuthenticated) {
            return "redirect:/topPage";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }

    @GetMapping("/topPage")
    public String topPage(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName(); // ログインユーザーの名前を取得
            model.addAttribute("loggedIn", true);
            model.addAttribute("username", username);
        } else {
            model.addAttribute("loggedIn", false);
        }
        return "topPage";
    }

}
