package com.example.learn_english_app.repository;

import com.example.learn_english_app.entity.GrammarError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GrammarErrorRepo extends JpaRepository<GrammarError,Long> {
    public List<GrammarError> findAllByPhraseId(Long id);
}
