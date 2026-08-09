package com.example.learn_english_app.controller;

import com.example.learn_english_app.dto.response.UserResponseDto;
import com.example.learn_english_app.dto.response.UserWordResponseDto;
import com.example.learn_english_app.service.UserService;
import com.example.learn_english_app.service.UserWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/user_words")
public class UserWordController {
    private final UserWordService userWordService;
    private final UserService userService;

    @GetMapping
    public List<UserWordResponseDto> findMyWords(@AuthenticationPrincipal Jwt jwt) {
        UserResponseDto userResponseDto = userService.findByUsername(jwt.getSubject());
        return userWordService.findByUserId(userResponseDto.getId());
    }

    @DeleteMapping("/words/{wordId}")
    public void deleteMyWord(@PathVariable Long wordId,@AuthenticationPrincipal Jwt jwt) {
        UserResponseDto userResponseDto = userService.findByUsername(jwt.getSubject());
        userWordService.deleteWord(userResponseDto.getId(), wordId);
    }

}
