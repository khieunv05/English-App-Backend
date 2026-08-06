package com.example.learn_english_app.repository;

import com.example.learn_english_app.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word,Long> {
    Word findByEnglish(String word);
}
