package com.example.learn_english_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordsByDateResponse {
    private LocalDate date;
    private List<WordResponseDto> words;
}
