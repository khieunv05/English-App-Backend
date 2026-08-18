package com.example.learn_english_app.controller;

import com.example.learn_english_app.dto.response.GeminiWordResponseDto;
import com.example.learn_english_app.form.GenerateWordRequest;
import com.example.learn_english_app.serviceImp.GeminiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/gemini")
@RequiredArgsConstructor
@Validated
public class GeminiController {
    private final GeminiService geminiService;
    @PostMapping("/generate")
    public GeminiWordResponseDto generateVocabulary(@RequestBody @Valid GenerateWordRequest generateWordRequest) {
        return geminiService.generateVocabulary(generateWordRequest.getWord());
    }
}
