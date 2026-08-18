package com.example.learn_english_app.repository;

import com.example.learn_english_app.entity.WordCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordCacheRepo extends JpaRepository<WordCache,Long> {
    Optional<WordCache> findByWord(String word);
}
