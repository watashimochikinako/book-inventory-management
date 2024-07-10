package com.example.demo.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.application.usecases.UserRegistrationUseCase;
import com.example.demo.presentation.forms.UserRegisterForm;

@Controller
public class UserRegistrationController {

    private final UserRegistrationUseCase userRegistrationUseCase;

    public UserRegistrationController(UserRegistrationUseCase userRegistrationUseCase) {
        this.userRegistrationUseCase = userRegistrationUseCase;
    }

    @GetMapping("/toRegister")
    public String toRegisterPage(Model model) {
        model.addAttribute("userRegisterForm", new UserRegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserRegisterForm userRegisterForm, Model model) {
        // ユーザ登録処理の実行
        boolean isRegistered = userRegistrationUseCase.register(
                userRegisterForm.getName(),
                userRegisterForm.getEmail(),
                userRegisterForm.getPassword());

        // 登録結果による処理
        if (isRegistered) {
            return "redirect:/login"; // 登録成功時のリダイレクト
        } else {
            model.addAttribute("error", true);
            return "register"; // 登録失敗時は登録ページを再表示
        }
    }
}
