package com.example.learn_english_app.serviceImp;

import com.example.learn_english_app.entity.User;
import com.example.learn_english_app.entity.UserWord;
import com.example.learn_english_app.form.WordCreateForm;
import com.example.learn_english_app.form.WordUpdateForm;
import com.example.learn_english_app.dto.response.WordResponseDto;
import com.example.learn_english_app.entity.Word;
import com.example.learn_english_app.mapper.WordMapper;
import com.example.learn_english_app.repository.UserRepo;
import com.example.learn_english_app.repository.UserWordRepo;
import com.example.learn_english_app.repository.WordRepository;
import com.example.learn_english_app.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordServiceImp implements WordService {
    private final WordRepository wordRepository;
    private  final UserRepo userRepo;
    private final UserWordRepo userWordRepo;
    @Override
    public WordResponseDto createWord(WordCreateForm dto) {
        User user = userRepo.findById(dto.getUserId()).orElseThrow();
        UserWord userWord = new UserWord();
        Word word = WordMapper.toEntity(dto);
        userWord.setUser(user);
        userWord.setWord(word);
        wordRepository.save(word);
        userWordRepo.save(userWord);
        return WordMapper.toResponse(word);
    }
    // Can check lai
    @Override
    public WordResponseDto updateWord(Long id, WordUpdateForm dto) {
        Word oldWord = getWordById(id);
        Word word = WordMapper.toEntity(oldWord,dto);
        wordRepository.save(word);
        return WordMapper.toResponse(word);
    }

    @Override
    public void deleteWordById(Long id) {
        wordRepository.deleteById(id);
    }

    @Override
    public Word getWordById(Long id) {
        return wordRepository.findById(id).orElseThrow(()-> new RuntimeException("Word Not Found"));
    }


}
