package com.example.learn_english_app.serviceImp;

import com.example.learn_english_app.dto.response.UserResponseDto;
import com.example.learn_english_app.entity.User;
import com.example.learn_english_app.form.UserCreateForm;
import com.example.learn_english_app.mapper.UserMapper;
import com.example.learn_english_app.repository.UserRepo;
import com.example.learn_english_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponseDto createUser(UserCreateForm userCreateForm) {
        User user = UserMapper.toEntity(userCreateForm);
        String encodedPassword = passwordEncoder.encode(userCreateForm.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return UserMapper.toResponse(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of()
        );
    }
}
