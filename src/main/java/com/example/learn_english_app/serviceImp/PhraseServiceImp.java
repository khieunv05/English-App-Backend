package com.example.learn_english_app.serviceImp;

import com.example.learn_english_app.dto.response.PhraseResponseDto;
import com.example.learn_english_app.entity.Phrase;
import com.example.learn_english_app.entity.User;
import com.example.learn_english_app.form.PhraseCreateForm;
import com.example.learn_english_app.form.PhraseUpdateForm;
import com.example.learn_english_app.mapper.PhraseMapper;
import com.example.learn_english_app.repository.GrammarErrorRepo;
import com.example.learn_english_app.repository.PhraseRepo;
import com.example.learn_english_app.repository.UserRepo;
import com.example.learn_english_app.service.PhraseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PhraseServiceImp implements PhraseService {
    private final PhraseRepo phraseRepo;
    private final UserRepo userRepo;
    private final GrammarErrorRepo grammarErrorRepo;
    @Override
    public List<PhraseResponseDto> getAllPhrase() {
        return phraseRepo.findAllByOrderByCreatedAtDesc().stream().map(PhraseMapper::toResponse).toList();
    }

    @Override
    public PhraseResponseDto updatePhrase(Long id, PhraseUpdateForm phraseUpdateForm) {
        Phrase phrase = phraseRepo.findById(id).orElseThrow(()-> new RuntimeException("Phrase Not Found"));
        PhraseMapper.toEntity(phrase, phraseUpdateForm);
        phraseRepo.save(phrase);
        return PhraseMapper.toResponse(phrase);
    }

    @Override
    public void deletePhrase(Long id) {
        phraseRepo.deleteById(id);
    }

    @Override
    public PhraseResponseDto createPhrase(PhraseCreateForm phraseCreateForm) {
        Phrase phrase = PhraseMapper.toEntity(phraseCreateForm);
        phrase.setUser(userRepo.findById(phraseCreateForm.getUserId()).orElseThrow());
        phraseRepo.save(phrase);
        return PhraseMapper.toResponse(phrase);
    }


}
