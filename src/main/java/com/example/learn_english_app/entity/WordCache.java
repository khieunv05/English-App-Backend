package com.example.learn_english_app.entity;

import com.example.learn_english_app.enums.Level;
import com.example.learn_english_app.enums.PartOfSpeech;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "word_cache")
@Data
public class WordCache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String word;

    private String pronunciation;
    private String vietnamese;

    @Column(length = 1000)
    private String example;

    @Column(length = 1000)
    private String exampleTranslation;

    @Enumerated(EnumType.STRING)
    @Column(name = "part_of_speech",nullable = false)
    private PartOfSpeech partOfSpeech;

    @Enumerated(EnumType.STRING)
    @Column(name = "level",nullable = false)
    private Level level;



    @CreationTimestamp
    private LocalDateTime createdAt;

}
