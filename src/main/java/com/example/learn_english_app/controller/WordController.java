package com.example.learn_english_app.controller;

import com.example.learn_english_app.form.WordCreateForm;
import com.example.learn_english_app.form.WordUpdateForm;
import com.example.learn_english_app.dto.response.WordResponseDto;
import com.example.learn_english_app.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/words")
@Validated
public class WordController {
    private final WordService wordService;
    @PostMapping
    public WordResponseDto createWord(@RequestBody WordCreateForm dto){
        return wordService.createWord(dto);
    }
    @PutMapping("/{id}")
    public WordResponseDto updateWord(@PathVariable Long id,@RequestBody WordUpdateForm dto){
        return wordService.updateWord(id,dto);
    }
    @DeleteMapping("/{id}")
    public void deleteWord(@PathVariable Long id){
        wordService.deleteWordById(id);
    }
}
