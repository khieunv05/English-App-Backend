package com.example.learn_english_app.service;

import com.example.learn_english_app.form.WordCreateForm;
import com.example.learn_english_app.form.WordUpdateForm;
import com.example.learn_english_app.dto.response.WordResponseDto;
import com.example.learn_english_app.entity.Word;

import java.util.List;

public interface WordService {
    public WordResponseDto createWord(WordCreateForm dto);
    public WordResponseDto updateWord(Long id, WordUpdateForm dto);
    public void deleteWordById(Long id);
    public Word getWordById(Long id);
}
