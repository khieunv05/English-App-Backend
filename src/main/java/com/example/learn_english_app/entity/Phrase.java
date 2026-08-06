package com.example.learn_english_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
@Entity(name = "phrases")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phrase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text",nullable = false)
    private String text;
    @Column(name = "score",nullable = false)
    private int score;
    @OneToMany(mappedBy = "phrase",cascade = CascadeType.ALL)
    private List<GrammarError> grammarErrors;
    private String correctedText;
    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
