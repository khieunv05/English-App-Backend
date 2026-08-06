package com.example.learn_english_app.mapper;

import com.example.learn_english_app.dto.response.UserResponseDto;
import com.example.learn_english_app.entity.User;
import com.example.learn_english_app.form.UserCreateForm;
import com.example.learn_english_app.form.UserUpdateForm;

public class UserMapper {
    public static User toEntity(UserCreateForm userCreateForm){
        User user = new User();
        user.setUsername(userCreateForm.getUsername());
        user.setPassword(userCreateForm.getPassword());
        return user;
    }
    public static User toEntity(User user,UserUpdateForm userUpdateForm){
        user.setPassword(userUpdateForm.getPassword());
        return user;
    }
    public static UserResponseDto toResponse(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        return userResponseDto;
    }
}
