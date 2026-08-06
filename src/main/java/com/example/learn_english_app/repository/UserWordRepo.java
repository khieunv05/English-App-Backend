package com.example.learn_english_app.repository;

import com.example.learn_english_app.entity.User;
import com.example.learn_english_app.entity.UserWord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserWordRepo extends JpaRepository<UserWord,Long> {
    public List<UserWord> findByUserId(Long id);
    public void deleteByUserIdAndWordId(Long userId,Long wordId);
}
