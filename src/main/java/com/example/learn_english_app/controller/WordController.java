package com.example.learn_english_app.controller;

import com.example.learn_english_app.dto.response.UserResponseDto;
import com.example.learn_english_app.form.WordCreateForm;
import com.example.learn_english_app.form.WordUpdateForm;
import com.example.learn_english_app.dto.response.WordResponseDto;
import com.example.learn_english_app.service.UserService;
import com.example.learn_english_app.service.WordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/words")
@Validated
public class WordController {
    private final WordService wordService;
    private final UserService userService;
    @PostMapping
    public WordResponseDto createWord(@Valid @RequestBody WordCreateForm dto, @AuthenticationPrincipal Jwt jwt){
        UserResponseDto userResponseDto = userService.findByUsername(jwt.getSubject());
        return wordService.createWord(userResponseDto.getId(),dto);
    }
    @PutMapping("/{id}")
    public WordResponseDto updateWord(@PathVariable Long id,@Valid @RequestBody WordUpdateForm dto){
        return wordService.updateWord(id,dto);
    }
    @DeleteMapping("/{id}")
    public void deleteWord(@PathVariable Long id){
        wordService.deleteWordById(id);
    }
}
