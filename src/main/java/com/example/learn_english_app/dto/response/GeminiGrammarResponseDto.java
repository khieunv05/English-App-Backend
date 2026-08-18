package com.example.learn_english_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeminiGrammarResponseDto {
    private String incorrect;
    private String correction;
    private String explanation;
}
