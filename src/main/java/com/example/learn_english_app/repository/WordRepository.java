package com.example.learn_english_app.repository;

import com.example.learn_english_app.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word,Long> {
    Optional<Word> findByEnglish(String word);
    List<Word> findAllByUserIdOrderByCreatedAtDesc(Long userId);

}
