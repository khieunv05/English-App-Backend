package com.example.learn_english_app.serviceImp;

import com.example.learn_english_app.dto.response.UserWordResponseDto;
import com.example.learn_english_app.entity.UserWord;
import com.example.learn_english_app.mapper.UserWordMapper;
import com.example.learn_english_app.repository.UserWordRepo;
import com.example.learn_english_app.service.UserWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserWordServiceImp implements UserWordService {
    private final UserWordRepo userWordRepo;

    @Override
    public List<UserWordResponseDto> findByUserId(Long id) {
        List<UserWord> userWordList = userWordRepo.findByUserId(id);
        List<UserWordResponseDto> userWordResponseDtoList = new LinkedList<>();
        for(UserWord userWord : userWordList){
            userWordResponseDtoList.add(UserWordMapper.toResponse(userWord));
        }
        return userWordResponseDtoList;
    }

    @Override
    public void deleteWord(Long userId,Long wordId) {
        userWordRepo.deleteByUserIdAndWordId(userId,wordId);
    }
}
