package com.example.demo.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.application.dtos.UserDTO;
import com.example.demo.application.services.UserDeleteService;
import com.example.demo.application.services.UserUpdateService;

/**
 * ユーザー情報に関するコントローラです。
 */
@Controller
public class UserProfileController {

    private final UserUpdateService userUpdateService;
    private final UserDeleteService userDeleteService;

    public UserProfileController(UserUpdateService userUpdateService, UserDeleteService userDeleteService) {
        this.userUpdateService = userUpdateService;
        this.userDeleteService = userDeleteService;
    }

    @GetMapping("/user-profile")
    public String showUpdateForm(Model model) {
        // 現在のユーザー情報を取得
        UserDTO userDTO = userUpdateService.getCurrentUserDTO();
        model.addAttribute("userDTO", userDTO);
        return "user-profile";
    }

    @PostMapping("/user-profile/update")
    public String updateUser(UserDTO userDTO, Model model) {
        // ユーザー情報の更新
        userUpdateService.updateUser(userDTO);
        return "user-profile";
    }

    @PostMapping("/user-profile/delete")
    public String deleteUser(Model model) {
        boolean success = userDeleteService.deleteUser();
        if (success) {
            model.addAttribute("successMessage", "ユーザーが正常に削除されました。");
            return "redirect:/toLogin";
        } else {
            model.addAttribute("errorMessage", "ユーザー削除に失敗しました。");
            return "user-profile";
        }
    }
    
}
