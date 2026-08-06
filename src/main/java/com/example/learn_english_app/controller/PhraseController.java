package com.example.learn_english_app.controller;

import com.example.learn_english_app.dto.response.PhraseResponseDto;
import com.example.learn_english_app.form.PhraseCreateForm;
import com.example.learn_english_app.form.PhraseUpdateForm;
import com.example.learn_english_app.service.PhraseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/phrases")
public class PhraseController {
    private final PhraseService phraseService;
    @GetMapping
    public List<PhraseResponseDto> getAllPhrases(){
        return phraseService.getAllPhrase();
    }
    @PostMapping
    public PhraseResponseDto createPhrase(@RequestBody PhraseCreateForm phraseCreateForm){
        return phraseService.createPhrase(phraseCreateForm);
    }
    @PutMapping("/{id}")
    public PhraseResponseDto updatePhrase(@PathVariable Long id,@RequestBody PhraseUpdateForm phraseUpdateForm){
        return phraseService.updatePhrase(id,phraseUpdateForm);
    }
    @DeleteMapping("/{id}")
    public void deletePhrase(@PathVariable Long id){
        phraseService.deletePhrase(id);
    }
}
