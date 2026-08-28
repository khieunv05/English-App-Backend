package com.example.learn_english_app.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateListWordReviewRequest {
    @NotNull
    private List<Long> wordIds;
}
