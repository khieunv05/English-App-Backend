package com.example.learn_english_app.service;

import com.example.learn_english_app.dto.response.WordsByDateResponse;
import com.example.learn_english_app.entity.User;
import com.example.learn_english_app.form.*;
import com.example.learn_english_app.dto.response.WordResponseDto;
import com.example.learn_english_app.entity.Word;

import java.util.List;
import java.util.Optional;

public interface WordService {
    WordResponseDto createWord(Long userId, WordCreateForm dto);
    WordResponseDto updateWord(Long userId, Long id, WordUpdateForm dto);
    void deleteWordById(Long userId, Long id);
    Word getWordById(Long id);
    List<WordsByDateResponse> getMyWords(Long userId);
    WordResponseDto updateFavorite(Long userId, Long id, WordUpdateFavoriteForm form);
    WordResponseDto updateReviewCount(Long userId,Long id);
    WordResponseDto getWordResponseById(Long userId,Long id);
}
