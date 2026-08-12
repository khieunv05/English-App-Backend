package com.example.learn_english_app.mapper;

import com.example.learn_english_app.dto.response.GrammarResponseDto;
import com.example.learn_english_app.dto.response.PhraseResponseDto;
import com.example.learn_english_app.entity.GrammarError;
import com.example.learn_english_app.entity.Phrase;
import com.example.learn_english_app.form.PhraseCreateForm;
import com.example.learn_english_app.form.PhraseUpdateForm;

import java.util.List;

public class PhraseMapper {
    public static Phrase toEntity(PhraseCreateForm phraseCreateForm){
        Phrase phrase = new Phrase();
        phrase.setText(phraseCreateForm.getText());
        phrase.setScore(phraseCreateForm.getScore());
        phrase.setCorrectedText(phraseCreateForm.getCorrectedText());
        List<GrammarError> errors = phraseCreateForm.getGrammarErrors() == null
                ? List.of()
                : phraseCreateForm.getGrammarErrors().stream()
                .map(errDto -> {
                    GrammarError error = new GrammarError();
                    error.setIncorrect(errDto.getIncorrect());
                    error.setCorrection(errDto.getCorrection());
                    error.setExplanation(errDto.getExplanation());
                    error.setPhrase(phrase);
                    return error;
                })
                .toList();

        phrase.setGrammarErrors(errors);
        return phrase;
    }
    public static Phrase toEntity(Phrase phrase,PhraseUpdateForm phraseUpdateForm){
        phrase.setText(phraseUpdateForm.getText());
        phrase.setScore(phraseUpdateForm.getScore());
        phrase.setCorrectedText(phraseUpdateForm.getCorrectedText());
        List<GrammarError> errors = phraseUpdateForm.getGrammarErrors() == null
                ? List.of()
                : phraseUpdateForm.getGrammarErrors().stream()
                .map(errDto -> {
                    GrammarError error = new GrammarError();
                    error.setIncorrect(errDto.getIncorrect());
                    error.setCorrection(errDto.getCorrection());
                    error.setExplanation(errDto.getExplanation());
                    error.setPhrase(phrase);
                    return error;
                })
                .toList();

        phrase.setGrammarErrors(errors);
        return phrase;
    }
    public static PhraseResponseDto toResponse(Phrase phrase){
        PhraseResponseDto phraseResponseDto = new PhraseResponseDto();
        phraseResponseDto.setId(phrase.getId());
        phraseResponseDto.setText(phrase.getText());
        phraseResponseDto.setScore(phrase.getScore());
        List<GrammarResponseDto> listGrammarResponseDto = GrammarErrorMapper.toResponse(phrase.getGrammarErrors());
        phraseResponseDto.setGrammarErrors(listGrammarResponseDto);
        phraseResponseDto.setCreatedAt(phrase.getCreatedAt());
        phraseResponseDto.setCorrectedText(phrase.getCorrectedText());
        phraseResponseDto.setUserId(phrase.getUser().getId());
        return phraseResponseDto;
    }
}
