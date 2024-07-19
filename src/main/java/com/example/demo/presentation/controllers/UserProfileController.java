package com.example.demo.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.application.dtos.UserDTO;
import com.example.demo.application.services.UserUpdateService;

/**
 * ユーザー情報に関するコントローラです。
 */
@Controller
public class UserProfileController {

    private final UserUpdateService userService;

    public UserProfileController(UserUpdateService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-profile")
    public String showUpdateForm(Model model) {
        // 現在のユーザー情報を取得
        UserDTO userDTO = userService.getCurrentUserDTO();
        model.addAttribute("userDTO", userDTO);
        return "user-profile";
    }

    @PostMapping("/user-profile/update")
    public String updateUser(UserDTO userDTO, Model model) {
        // ユーザー情報の更新
        userService.updateUser(userDTO);
        
        return "user-profile";
    }

    // ユーザー情報の削除（未実装）
    /*
    @PostMapping("/user-profile/delete")
    public String deleteUser(Model model) {
        boolean isDeleted = userService.deleteCurrentUser();
        if (isDeleted) {
            return "redirect:/toLogin";
        } else {
            model.addAttribute("error", "ユーザー情報の削除に失敗しました。");
            return "user-profile";
        }
    }
    */
}
