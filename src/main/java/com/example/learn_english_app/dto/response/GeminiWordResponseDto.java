package com.example.learn_english_app.dto.response;

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
    private boolean valid;
}
