package com.example.learn_english_app.mapper;

import com.example.learn_english_app.dto.response.UserWordResponseDto;
import com.example.learn_english_app.dto.response.WordResponseDto;
import com.example.learn_english_app.entity.UserWord;

public class UserWordMapper {
    public static UserWordResponseDto toResponse(UserWord userWord){
        UserWordResponseDto userWordResponseDto = new UserWordResponseDto();
        userWordResponseDto.setId(userWord.getId());
        WordResponseDto wordResponseDto = WordMapper.toResponse(userWord.getWord());
        userWordResponseDto.setWordResponseDto(wordResponseDto);
        userWordResponseDto.setFavorite(userWord.isFavorite());
        userWordResponseDto.setNextReview(userWord.getNextReview());
        userWordResponseDto.setReviewCount(userWord.getReviewCount());
        return userWordResponseDto;
    }
}
