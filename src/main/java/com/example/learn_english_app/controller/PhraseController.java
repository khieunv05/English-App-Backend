package com.example.learn_english_app.controller;

import com.example.learn_english_app.dto.response.PhraseResponseDto;
import com.example.learn_english_app.form.PhraseCreateForm;
import com.example.learn_english_app.form.PhraseUpdateForm;
import com.example.learn_english_app.service.PhraseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/phrases")
@Validated
public class PhraseController {
    private final PhraseService phraseService;

    @GetMapping
    public List<PhraseResponseDto> getAllPhrases(@AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        return phraseService.getAllPhraseById(userId);
    }

    @PostMapping
    public PhraseResponseDto createPhrase(@Valid @RequestBody PhraseCreateForm phraseCreateForm,
                                          @AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        return phraseService.createPhrase(userId, phraseCreateForm);
    }

    @DeleteMapping("/{id}")
    public void deletePhrase(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        phraseService.deletePhrase(userId, id);
    }

    @GetMapping("/{id}")
    public PhraseResponseDto findById(@PathVariable Long id,@AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        return phraseService.findById(userId,id);
    }
}