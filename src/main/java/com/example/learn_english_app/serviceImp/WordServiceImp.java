package com.example.learn_english_app.serviceImp;

import com.example.learn_english_app.dto.response.WordsByDateResponse;
import com.example.learn_english_app.entity.User;
import com.example.learn_english_app.form.*;
import com.example.learn_english_app.dto.response.WordResponseDto;
import com.example.learn_english_app.entity.Word;
import com.example.learn_english_app.mapper.WordMapper;
import com.example.learn_english_app.repository.UserRepo;
import com.example.learn_english_app.repository.WordRepository;
import com.example.learn_english_app.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WordServiceImp implements WordService {
    private final WordRepository wordRepository;
    private final UserRepo userRepo;
    private final SrsService srsService;
    @Override
    @Transactional
    public WordResponseDto createWord(Long userId, WordCreateForm dto) {
        User user = userRepo.findById(userId).orElseThrow();
        Word word = WordMapper.toEntity(dto);
        word.setUser(user);
        wordRepository.save(word);
        return WordMapper.toResponse(word);
    }

    @Override
    @Transactional
    public WordResponseDto updateWord(Long userId, Long id, WordUpdateForm dto) {
        Word oldWord = getWordById(id);
        checkOwnership(oldWord, userId);
        Word word = WordMapper.toEntity(oldWord, dto);
        wordRepository.save(word);
        return WordMapper.toResponse(word);
    }


    @Override
    @Transactional
    public void deleteWordById(Long userId, Long id) {
        Word word = getWordById(id);
        checkOwnership(word, userId);
        wordRepository.deleteById(id);
    }

    @Override
    public Word getWordById(Long id) {
        return wordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Word Not Found"));
    }

    @Override
    public List<WordsByDateResponse> getMyWords(Long userId) {
        List<Word> words = wordRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
        Map<LocalDate,List<WordResponseDto>> group = words.stream().collect(
                Collectors.groupingBy(
                       word->word.getCreatedAt().toLocalDate(),
                        LinkedHashMap::new,
                        Collectors.mapping(WordMapper::toResponse,Collectors.toList())
                )

               );
        return group.entrySet().stream()
                .map(
                        item -> new WordsByDateResponse(
                                item.getKey(),
                                item.getValue()
                        )
                ).toList();
    }

    @Override
    public WordResponseDto updateFavorite(Long userId, Long id, WordUpdateFavoriteForm form) {
        Word word = getWordById(id);
        checkOwnership(word,userId);
        WordMapper.toEntity(word,form);
        wordRepository.save(word);
        return WordMapper.toResponse(word);
    }

    @Override
    public WordResponseDto updateReviewCount(Long userId, Long id) {
        Word word = getWordById(id);
        checkOwnership(word,userId);
        word.setReviewCount(word.getReviewCount()+1);
        word.setNextReview(srsService.calculateNextReview(word.getReviewCount()));
        wordRepository.save(word);
        return WordMapper.toResponse(word);
    }

    @Override
    public WordResponseDto getWordResponseById(Long userId, Long id) {
        Word word = getWordById(id);
        checkOwnership(word,userId);
        return WordMapper.toResponse(word);
    }


    private void checkOwnership(Word word, Long userId) {
        if (!word.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Bạn không có quyền thao tác trên từ này");
        }
    }
}