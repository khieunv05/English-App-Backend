package com.example.learn_english_app.dto.response;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class PhraseResponseDto {
    private Long id;
    private String text;
    private int score;
    private List<GrammarResponseDto> grammarErrors;
    private String correctedText;
    private LocalDateTime createdAt;
}
