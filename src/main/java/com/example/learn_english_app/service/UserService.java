package com.example.learn_english_app.service;

import com.example.learn_english_app.dto.response.UserResponseDto;
import com.example.learn_english_app.form.UserCreateForm;

public interface UserService {
    public UserResponseDto createUser(UserCreateForm userCreateForm);
    public UserResponseDto findByUsername(String username);
}
