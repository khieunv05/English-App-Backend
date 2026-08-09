package com.example.learn_english_app.form;

import com.example.learn_english_app.enums.Level;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WordUpdateForm {
    @NotBlank
    private String english;
    @NotBlank
    private String vietnamese;
    @NotBlank
    private String example;
    @NotBlank
    private String exampleTranslation;
    @NotBlank
    private String pronunciation;
    @NotBlank
    private String partOfSpeech;
    @NotBlank
    private Level level;
}
