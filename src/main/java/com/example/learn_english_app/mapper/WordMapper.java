package com.example.learn_english_app.mapper;

import com.example.learn_english_app.form.WordCreateForm;
import com.example.learn_english_app.form.WordUpdateForm;
import com.example.learn_english_app.dto.response.WordResponseDto;
import com.example.learn_english_app.entity.Word;

public class WordMapper {
    public static Word toEntity(WordCreateForm dto){
        Word word = new Word();
        word.setEnglish(dto.getEnglish());
        word.setLevel(dto.getLevel());
        word.setExample(dto.getExample());
        word.setPronunciation(dto.getPronunciation());
        word.setVietnamese(dto.getVietnamese());
        word.setExampleTranslation(dto.getExampleTranslation());
        word.setPartOfSpeech(dto.getPartOfSpeech());
        return word;
    }
    public static Word toEntity(Word word, WordUpdateForm dto){
        word.setEnglish(dto.getEnglish());
        word.setLevel(dto.getLevel());
        word.setExample(dto.getExample());
        word.setPronunciation(dto.getPronunciation());
        word.setVietnamese(dto.getVietnamese());
        word.setExampleTranslation(dto.getExampleTranslation());
        word.setPartOfSpeech(dto.getPartOfSpeech());
        return word;
    }
    public static WordResponseDto toResponse(Word word){
        WordResponseDto dto = new WordResponseDto();
        dto.setEnglish(word.getEnglish());
        dto.setLevel(word.getLevel());
        dto.setExample(word.getExample());
        dto.setPronunciation(word.getPronunciation());
        dto.setVietnamese(word.getVietnamese());
        dto.setExampleTranslation(word.getExampleTranslation());
        dto.setPartOfSpeech(word.getPartOfSpeech());
        dto.setId(word.getId());
        return dto;
    }
}
