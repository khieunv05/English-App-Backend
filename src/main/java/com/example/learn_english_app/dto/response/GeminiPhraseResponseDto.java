package com.example.learn_english_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeminiPhraseResponseDto {
    private String text;
    private int score;
    private List<GeminiGrammarResponseDto> grammarErrors;
    private String correctedText;
}
