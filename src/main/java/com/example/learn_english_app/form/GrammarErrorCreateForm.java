package com.example.learn_english_app.form;

import lombok.Data;

@Data
public class GrammarErrorCreateForm {
    private String incorrect;
    private String correction;
    private String explanation;
}
