package com.example.learn_english_app.form;

import com.example.learn_english_app.entity.GrammarError;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;
@Data
public class PhraseUpdateForm {
    @NotBlank
    private String text;
    @NotBlank
    @Max(value = 10)
    @Min(value = 0)
    private int score;
    @NotBlank
    private List<GrammarError> grammarErrors;
    @NotBlank
    private String correctedText;

    private Long userId;
}
