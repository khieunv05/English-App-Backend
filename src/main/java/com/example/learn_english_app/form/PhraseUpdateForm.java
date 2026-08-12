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
    @Max(value = 10)
    @Min(value = 0)
    private int score;
    private List<GrammarErrorUpdateForm> grammarErrors;
    @NotBlank
    private String correctedText;
}
