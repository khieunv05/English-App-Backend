package com.example.learn_english_app.controller;

import com.example.learn_english_app.dto.response.WordsByDateResponse;
import com.example.learn_english_app.form.UpdateListWordReviewRequest;
import com.example.learn_english_app.form.WordCreateForm;
import com.example.learn_english_app.form.WordUpdateFavoriteForm;
import com.example.learn_english_app.form.WordUpdateForm;
import com.example.learn_english_app.dto.response.WordResponseDto;
import com.example.learn_english_app.service.WordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/words")
@Validated
public class WordController {
    private final WordService wordService;

    @PostMapping
    public WordResponseDto createWord(@Valid @RequestBody WordCreateForm dto, @AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        return wordService.createWord(userId, dto);
    }

    @PutMapping("/{id}")
    public WordResponseDto updateWord(@PathVariable Long id,
                                      @Valid @RequestBody WordUpdateForm dto,
                                      @AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        return wordService.updateWord(userId, id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteWord(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        wordService.deleteWordById(userId, id);
    }
    @PutMapping("/{id}/favorite")
    public WordResponseDto updateFavorite(@PathVariable Long id,
                                          @Valid @RequestBody WordUpdateFavoriteForm dto,
                                          @AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        return wordService.updateFavorite(userId,id,dto);
    }
    @PutMapping("/{id}/review")
    public WordResponseDto reviewWord(@PathVariable Long id,
                                      @AuthenticationPrincipal Jwt jwt) {
        Long userId = jwt.getClaim("userId");
        return wordService.updateReviewCount(userId, id);
    }

    @GetMapping("/me")
    public List<WordsByDateResponse> getMyWords(@AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        return wordService.getMyWords(userId);
    }

    @GetMapping("/{id}")
    public WordResponseDto getWordById(@PathVariable Long id,@AuthenticationPrincipal Jwt jwt){
        Long userId = jwt.getClaim("userId");
        return wordService.getWordResponseById(userId,id);
    }
    @PutMapping("/review")
    public List<WordResponseDto> updateListWordReview(@AuthenticationPrincipal Jwt jwt,
                                                      @RequestBody UpdateListWordReviewRequest updateListWordReviewRequest){
        Long userId = jwt.getClaim("userId");
        return wordService.updateListWordReview(userId,updateListWordReviewRequest);
    }
}