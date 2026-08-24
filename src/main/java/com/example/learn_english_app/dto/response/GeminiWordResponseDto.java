package com.example.learn_english_app.dto.response;

import com.example.learn_english_app.enums.Level;
import com.example.learn_english_app.enums.PartOfSpeech;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GeminiWordResponseDto {
    private String english;
    private String pronunciation;
    private String vietnamese;
    private String example;
    private String exampleTranslation;
    private Level level;
    private PartOfSpeech partOfSpeech;
    private boolean valid;
}
