package com.example.learn_english_app.serviceImp;

import com.example.learn_english_app.dto.response.PhraseResponseDto;
import com.example.learn_english_app.entity.Phrase;
import com.example.learn_english_app.form.PhraseCreateForm;
import com.example.learn_english_app.form.PhraseUpdateForm;
import com.example.learn_english_app.mapper.PhraseMapper;
import com.example.learn_english_app.repository.GrammarErrorRepo;
import com.example.learn_english_app.repository.PhraseRepo;
import com.example.learn_english_app.repository.UserRepo;
import com.example.learn_english_app.service.PhraseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhraseServiceImp implements PhraseService {
    private final PhraseRepo phraseRepo;
    private final UserRepo userRepo;
    private final GrammarErrorRepo grammarErrorRepo;

    @Override
    public List<PhraseResponseDto> getAllPhraseById(Long userId) {
        return phraseRepo.findAllByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(PhraseMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public PhraseResponseDto createPhrase(Long userId, PhraseCreateForm dto) {
        Phrase phrase = PhraseMapper.toEntity(dto);
        phrase.setUser(userRepo.findById(userId).orElseThrow());
        phraseRepo.save(phrase);
        return PhraseMapper.toResponse(phrase);
    }


    @Override
    @Transactional
    public void deletePhrase(Long userId, Long id) {
        Phrase phrase = getPhraseEntity(id);
        checkOwnership(phrase, userId);
        phraseRepo.deleteById(id);
    }

    @Override
    public PhraseResponseDto findById(Long userId, Long id) {
        Phrase phrase = getPhraseEntity(id);
        checkOwnership(phrase,userId);
        return PhraseMapper.toResponse(phrase);
    }

    private Phrase getPhraseEntity(Long id) {
        return phraseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Phrase Not Found"));
    }

    private void checkOwnership(Phrase phrase, Long userId) {
        if (!phrase.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Bạn không có quyền thao tác trên phrase này");
        }
    }
}