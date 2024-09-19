package com.example.demo.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.application.dtos.UserDTO;
import com.example.demo.application.services.UserDeleteService;
import com.example.demo.application.services.UserUpdateService;
import com.example.demo.presentation.forms.UserUpdateForm;

import jakarta.validation.Valid;

/**
 * ユーザー情報に関するコントローラです。
 * 
 * このコントローラは、ユーザー情報の更新および削除に関するエンドポイントを提供します。
 */
@Controller
public class UserProfileController {

    private final UserUpdateService userUpdateService; // ユーザー情報の更新サービス
    private final UserDeleteService userDeleteService; // ユーザー削除サービス

    /**
     * UserProfileControllerのコンストラクタ
     *
     */
    public UserProfileController(UserUpdateService userUpdateService, UserDeleteService userDeleteService) {
        this.userUpdateService = userUpdateService;
        this.userDeleteService = userDeleteService;
    }

    /**
     * ユーザープロフィール更新フォームを表示します。
     * 
     * @param model モデルオブジェクト
     * @return 更新フォームビュー
     */
    @GetMapping("/user-profile")
    public String showUpdateForm(Model model) {
        // 現在のユーザー情報を取得
        UserDTO userDTO = userUpdateService.getCurrentUserDTO();

        // UserUpdateForm に現在の情報をセット
        UserUpdateForm userUpdateForm = new UserUpdateForm();
        userUpdateForm.setName(userDTO.getName());
        userUpdateForm.setEmail(userDTO.getEmail());

        // フォームオブジェクトをモデルに追加
        model.addAttribute("userUpdateForm", userUpdateForm);
        return "user-profile"; // フォームのビューを返す
    }

    /**
     * ユーザー情報を更新します。
     * 
     * @param userUpdateForm 更新フォームオブジェクト
     * @param result         バリデーション結果
     * @param model          モデルオブジェクト
     * @return 更新成功時のリダイレクトまたはエラーがあれば再表示
     */
    @PostMapping("/user-profile/update")
    public String updateUser(@Valid @ModelAttribute UserUpdateForm userUpdateForm,
            BindingResult result, Model model) {

        // パスワードが入力されている場合のみバリデーションチェックを実施
        if (userUpdateForm.getPassword() != null && !userUpdateForm.getPassword().isEmpty()) {
            if (userUpdateForm.getPassword().length() < 8) {
                result.rejectValue("password", "Size.userUpdateForm.password", "パスワードは少なくとも8文字でなければなりません。");
            }
        }

        // バリデーションエラーがある場合は再表示
        if (result.hasErrors()) {
            return "user-profile";
        }

        // UserUpdateForm から UserDTO に変換
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userUpdateForm.getName());
        userDTO.setEmail(userUpdateForm.getEmail());

        // パスワードが設定されている場合のみ設定
        if (userUpdateForm.getPassword() != null && !userUpdateForm.getPassword().isEmpty()) {
            userDTO.setPassword(userUpdateForm.getPassword());
        }

        // ユーザー情報の更新
        boolean success = userUpdateService.updateUser(userDTO);

        // 更新結果による処理
        if (success) {
            return "redirect:/toLogin"; // 更新成功時のリダイレクト
        } else {
            model.addAttribute("errorMessage", "ユーザー情報の更新に失敗しました。");
            return "user-profile"; // 更新失敗時は再表示
        }
    }

    /**
     * ユーザーを削除します。
     * 
     * @param model モデルオブジェクト
     * @return 削除成功時のリダイレクトまたはエラーがあれば再表示
     */
    @PostMapping("/user-profile/delete")
    public String deleteUser(Model model) {
        // ユーザー削除処理
        boolean success = userDeleteService.deleteUser();

        // 削除結果による処理
        if (success) {
            model.addAttribute("successMessage", "ユーザーが正常に削除されました。");
            return "redirect:/toLogin"; // 削除成功時のリダイレクト
        } else {
            model.addAttribute("errorMessage", "ユーザー削除に失敗しました。");
            return "user-profile"; // 削除失敗時は再表示
        }
    }
}
