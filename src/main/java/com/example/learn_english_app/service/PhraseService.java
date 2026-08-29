package com.example.learn_english_app.service;

import com.example.learn_english_app.dto.response.PhraseResponseDto;
import com.example.learn_english_app.entity.Phrase;
import com.example.learn_english_app.form.PhraseCreateForm;
import com.example.learn_english_app.form.PhraseUpdateForm;

import java.util.List;
import java.util.Optional;

public interface PhraseService {
    List<PhraseResponseDto> getAllPhraseById(Long userId);
    PhraseResponseDto createPhrase(Long userId, PhraseCreateForm dto);
    void deletePhrase(Long userId, Long id);
    PhraseResponseDto findById(Long userId,Long id);
}