package com.example.learn_english_app.service;

import com.example.learn_english_app.dto.response.PhraseResponseDto;
import com.example.learn_english_app.form.PhraseCreateForm;
import com.example.learn_english_app.form.PhraseUpdateForm;

import java.util.List;

public interface PhraseService {
    public List<PhraseResponseDto> getAllPhraseById(Long id);
    public PhraseResponseDto updatePhrase(Long phraseId, PhraseUpdateForm phraseUpdateForm);
    public void deletePhrase(Long id);
    public PhraseResponseDto createPhrase(Long userId,PhraseCreateForm phraseCreateForm);
    public PhraseResponseDto findById(Long id);
}
