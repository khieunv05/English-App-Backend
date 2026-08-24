package com.example.learn_english_app.form;

import com.example.learn_english_app.enums.Level;
import com.example.learn_english_app.enums.PartOfSpeech;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private PartOfSpeech partOfSpeech;
    @NotNull
    private Level level;
}
