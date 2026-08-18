package com.example.learn_english_app.entity;

import jakarta.persistence.*;
import lombok.Data;

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

    private LocalDateTime createdAt;

}
