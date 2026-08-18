package com.example.learn_english_app.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenerateWordRequest {
    @NotBlank
    private String word;
}
