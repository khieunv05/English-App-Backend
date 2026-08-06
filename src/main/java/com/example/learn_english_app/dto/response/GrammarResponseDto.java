package com.example.learn_english_app.dto.response;

import lombok.Data;

@Data
public class GrammarResponseDto {
    private Long id;
    private String incorrect;
    private String correction;
    private String explanation;
}
