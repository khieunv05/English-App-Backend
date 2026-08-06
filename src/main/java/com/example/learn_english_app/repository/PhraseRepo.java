package com.example.learn_english_app.repository;
import com.example.learn_english_app.entity.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PhraseRepo extends JpaRepository<Phrase,Long> {
    public List<Phrase> findAllByOrderByCreatedAtDesc();
}
