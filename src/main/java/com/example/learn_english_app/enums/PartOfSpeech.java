package com.example.learn_english_app.enums;
import com.fasterxml.jackson.annotation.JsonProperty;
public enum PartOfSpeech {
    @JsonProperty("noun") NOUN,
    @JsonProperty("verb") VERB,
    @JsonProperty("adjective") ADJECTIVE,
    @JsonProperty("adverb") ADVERB,
    @JsonProperty("preposition") PREPOSITION,
    @JsonProperty("conjunction") CONJUNCTION,
    @JsonProperty("pronoun") PRONOUN,
    @JsonProperty("interjection") INTERJECTION
}
