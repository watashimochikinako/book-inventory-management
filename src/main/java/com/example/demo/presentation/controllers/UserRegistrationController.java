package com.example.demo.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.application.services.UserRegistrationService;
import com.example.demo.presentation.forms.UserRegisterForm;

/**
 * ユーザー登録に関するコントローラです。
 */
@Controller
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    /**
     * UserRegistrationControllerのコンストラクターです。
     *
     * @param serRegistrationService ユーザー登録サービスのインスタンス
     */
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    /**
     * 登録ページへの遷移を処理します。
     *
     * @param model モデルオブジェクト
     * @return 登録ページのビュー名
     */
    @GetMapping("/toRegister")
    public String toRegisterPage(Model model) {
        model.addAttribute("userRegisterForm", new UserRegisterForm());
        return "user-register";
    }

    /**
     * ユーザーの登録処理を行います。
     *
     * @param userRegisterForm ユーザー登録フォームオブジェクト
     * @param model モデルオブジェクト
     * @return 登録成功時はログインページへリダイレクト、失敗時は再度登録ページを表示
     */
    @PostMapping("/register")
    public String registerUser(UserRegisterForm userRegisterForm, Model model) {
        // ユーザー登録処理の実行
        boolean isRegistered = userRegistrationService.registerUser(
                userRegisterForm.getName(),
                userRegisterForm.getEmail(),
                userRegisterForm.getPassword());

        // 登録結果による処理
        if (isRegistered) {
            return "redirect:/toLogin"; // 登録成功時のリダイレクト
        } else {
            model.addAttribute("error", true);
            return "user-register"; // 登録失敗時は登録ページを再表示
        }
    }
}
