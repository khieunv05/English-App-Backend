package com.example.learn_english_app.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WordUpdateFavoriteForm {
    @NotNull
    private boolean favorite;
}
