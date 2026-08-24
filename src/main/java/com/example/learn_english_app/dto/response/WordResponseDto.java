package com.example.learn_english_app.dto.response;

import com.example.learn_english_app.enums.Level;
import com.example.learn_english_app.enums.PartOfSpeech;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WordResponseDto {
    private Long id;
    private String english;
    private String vietnamese;
    private String example;
    private String exampleTranslation;
    private String pronunciation;
    private PartOfSpeech partOfSpeech;
    private Level level;
    private int reviewCount;
    private LocalDateTime nextReview;
    private boolean favorite;
    private LocalDateTime createdAt;
}
