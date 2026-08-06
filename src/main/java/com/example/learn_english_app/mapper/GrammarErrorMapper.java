package com.example.learn_english_app.mapper;

import com.example.learn_english_app.dto.response.GrammarResponseDto;
import com.example.learn_english_app.entity.GrammarError;

import java.util.LinkedList;
import java.util.List;

public class GrammarErrorMapper {
    public static List<GrammarResponseDto> toResponse(List<GrammarError> grammarErrors){
        List<GrammarResponseDto> listGrammarResponseDto = new LinkedList<>();
        for(GrammarError grammarError : grammarErrors){
            GrammarResponseDto grammarResponseDto = new GrammarResponseDto();
            grammarResponseDto.setId(grammarError.getId());
            grammarResponseDto.setCorrection(grammarError.getCorrection());
            grammarResponseDto.setExplanation(grammarError.getExplanation());
            grammarResponseDto.setIncorrect(grammarError.getIncorrect());
            listGrammarResponseDto.add(grammarResponseDto);
        }
        return listGrammarResponseDto;
    }
}
