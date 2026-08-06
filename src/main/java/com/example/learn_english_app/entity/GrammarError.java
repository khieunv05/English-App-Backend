package com.example.learn_english_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "grammar_errors")
public class GrammarError {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String incorrect;
    private String correction;
    private String explanation;
    @ManyToOne
    @JoinColumn(name = "phrase_id")
    private Phrase phrase;
}
