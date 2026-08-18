package com.example.learn_english_app.controller;
import com.example.learn_english_app.dto.response.GeminiPhraseResponseDto;
import com.example.learn_english_app.dto.response.GeminiWordResponseDto;
import com.example.learn_english_app.form.GeminiGenerateWordRequest;
import com.example.learn_english_app.form.GeminiPhraseRequest;
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
    public GeminiWordResponseDto generateVocabulary(@RequestBody @Valid GeminiGenerateWordRequest generateWordRequest) {
        return geminiService.generateVocabulary(generateWordRequest.getWord());
    }

    @PostMapping("/phrase")
    public GeminiPhraseResponseDto scorePhrase(@RequestBody @Valid GeminiPhraseRequest geminiPhraseRequest){
        return geminiService.callGeminiPhrase(geminiPhraseRequest.getText());
    }
}
