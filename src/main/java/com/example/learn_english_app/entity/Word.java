package com.example.learn_english_app.entity;

import com.example.learn_english_app.enums.Level;
import com.example.learn_english_app.enums.PartOfSpeech;
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
// Can them dieu kien unique cho user id và english, lưu vào db dạng chữ thường
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

    @Enumerated(EnumType.STRING)
    @Column(name = "part_of_speech",nullable = false)
    private PartOfSpeech partOfSpeech;

    @Enumerated(EnumType.STRING)
    @Column(name = "level",nullable = false)
    private Level level;

    @Column(name = "review_count")
    private int reviewCount;
    @Column(name = "next_review")
    private LocalDateTime nextReview;
    @Column(name = "favorite")
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
