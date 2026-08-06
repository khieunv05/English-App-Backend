package com.example.learn_english_app.controller;

import com.example.learn_english_app.dto.response.UserResponseDto;
import com.example.learn_english_app.form.UserCreateForm;
import com.example.learn_english_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;
    @PostMapping
    public UserResponseDto createUser(@RequestBody UserCreateForm userCreateForm){
        return userService.createUser(userCreateForm);
    }
}
