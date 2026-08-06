package com.example.learn_english_app.service;

import com.example.learn_english_app.dto.response.PhraseResponseDto;
import com.example.learn_english_app.form.PhraseCreateForm;
import com.example.learn_english_app.form.PhraseUpdateForm;

import java.util.List;

public interface PhraseService {
    public List<PhraseResponseDto> getAllPhrase();
    public PhraseResponseDto updatePhrase(Long id, PhraseUpdateForm phraseUpdateForm);
    public void deletePhrase(Long id);
    public PhraseResponseDto createPhrase(PhraseCreateForm phraseCreateForm);
}
