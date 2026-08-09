package com.example.learn_english_app.controller;

import com.example.learn_english_app.dto.response.UserWordResponseDto;
import com.example.learn_english_app.repository.UserRepo;
import com.example.learn_english_app.service.UserWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/user_words")
public class UserWordController {
    private final UserWordService userWordService;
    private final UserRepo userRepo;

    @GetMapping("/users/{id}")
    public List<UserWordResponseDto> findMyWords(@PathVariable Long id) {
        return userWordService.findByUserId(id);
    }

    @DeleteMapping("/users/{userId}/words/{wordId}")
    public void deleteMyWord(@PathVariable Long userId,@PathVariable Long wordId) {
        userWordService.deleteWord(userId, wordId);
    }

}
