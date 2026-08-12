package com.example.learn_english_app.entity;

import com.example.learn_english_app.enums.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Entity
@Table(name = "words")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "english",nullable = false)
    private String english;

    @Column(name = "vietnamese",nullable = false)
    private String vietnamese;

    @Column(name = "example",nullable = false)
    private String example;

    @Column(name = "example_translation",nullable = false)
    private String exampleTranslation;

    @Column(name = "pronunciation",nullable = false)
    private String pronunciation;

    @Column(name = "part_of_speech",nullable = false)
    private String partOfSpeech;

    @Enumerated(EnumType.STRING)
    @Column(name = "level",nullable = false)
    private Level level;

    private int reviewCount;
    private LocalDateTime nextReview;
    private boolean favorite;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
