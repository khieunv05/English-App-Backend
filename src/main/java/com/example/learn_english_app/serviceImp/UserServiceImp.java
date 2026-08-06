package com.example.learn_english_app.serviceImp;

import com.example.learn_english_app.dto.response.UserResponseDto;
import com.example.learn_english_app.entity.User;
import com.example.learn_english_app.form.UserCreateForm;
import com.example.learn_english_app.mapper.UserMapper;
import com.example.learn_english_app.repository.UserRepo;
import com.example.learn_english_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepo userRepo;
    @Override
    public UserResponseDto createUser(UserCreateForm userCreateForm) {
        User user = UserMapper.toEntity(userCreateForm);
        userRepo.save(user);
        return UserMapper.toResponse(user);
    }
}
