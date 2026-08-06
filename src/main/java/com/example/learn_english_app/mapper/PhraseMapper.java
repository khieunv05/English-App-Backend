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
        phrase.setGrammarErrors(phraseCreateForm.getGrammarErrors());
        if(phrase.getGrammarErrors() != null){
            for(GrammarError grammarError : phrase.getGrammarErrors()){
                grammarError.setPhrase(phrase);
            }
        }
        return phrase;
    }
    public static Phrase toEntity(Phrase phrase,PhraseUpdateForm phraseUpdateForm){
        phrase.setText(phraseUpdateForm.getText());
        phrase.setScore(phraseUpdateForm.getScore());
        phrase.setCorrectedText(phraseUpdateForm.getCorrectedText());
        phrase.setGrammarErrors(phraseUpdateForm.getGrammarErrors());
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
        return phraseResponseDto;
    }
}
