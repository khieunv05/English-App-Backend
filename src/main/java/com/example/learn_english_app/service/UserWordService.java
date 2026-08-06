package com.example.learn_english_app.service;

import com.example.learn_english_app.dto.response.UserWordResponseDto;

import java.util.List;

public interface UserWordService {
    public List<UserWordResponseDto> findByUserId(Long id);
    public void deleteWord(Long userId,Long wordId);
}
