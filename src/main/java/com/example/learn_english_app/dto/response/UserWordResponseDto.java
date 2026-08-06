package com.example.learn_english_app.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class UserWordResponseDto {
    private Long id;

    private WordResponseDto wordResponseDto;

    private int reviewCount;

    private LocalDateTime nextReview;

    private boolean favorite;
}
