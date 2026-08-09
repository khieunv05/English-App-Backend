package com.example.learn_english_app.controller;

import com.example.learn_english_app.dto.response.PhraseResponseDto;
import com.example.learn_english_app.dto.response.UserResponseDto;
import com.example.learn_english_app.form.PhraseCreateForm;
import com.example.learn_english_app.form.PhraseUpdateForm;
import com.example.learn_english_app.service.PhraseService;
import com.example.learn_english_app.service.UserService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/v1/phrases")
@Validated
public class PhraseController {
    private final PhraseService phraseService;
    private final UserService userService;
    @GetMapping
    public List<PhraseResponseDto> getAllPhrases(@AuthenticationPrincipal Jwt jwt){
        UserResponseDto userResponseDto = userService.findByUsername(jwt.getSubject());
        return phraseService.getAllPhraseById(userResponseDto.getId());
    }
    @PostMapping
    public PhraseResponseDto createPhrase(@Valid @RequestBody PhraseCreateForm phraseCreateForm,@AuthenticationPrincipal Jwt jwt){
        UserResponseDto userResponseDto = userService.findByUsername(jwt.getSubject());
        return phraseService.createPhrase(userResponseDto.getId(),phraseCreateForm);
    }
    @PutMapping("/{id}")
    public PhraseResponseDto updatePhrase(@PathVariable Long id,@Valid @RequestBody PhraseUpdateForm phraseUpdateForm,@AuthenticationPrincipal Jwt jwt){
        UserResponseDto userResponseDto = userService.findByUsername(jwt.getSubject());
        PhraseResponseDto phraseResponseDto = phraseService.findById(id);
        if(!Objects.equals(phraseResponseDto.getUserId(), userResponseDto.getId())){
            throw new AccessDeniedException("Bạn không có quyền sửa thông tin này");
        }
        return phraseService.updatePhrase(id,phraseUpdateForm);
    }
    @DeleteMapping("/{id}")
    public void deletePhrase(@PathVariable Long id,@AuthenticationPrincipal Jwt jwt){
        UserResponseDto userResponseDto = userService.findByUsername(jwt.getSubject());
        PhraseResponseDto phraseResponseDto = phraseService.findById(id);
        if(!Objects.equals(phraseResponseDto.getUserId(), userResponseDto.getId())){
            throw new AccessDeniedException("Bạn không có quyền xóa thông tin này");
        }
        phraseService.deletePhrase(id);
    }
}
