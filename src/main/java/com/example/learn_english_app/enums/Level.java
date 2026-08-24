package com.example.learn_english_app.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Level {
    @JsonProperty("A1") A1,
    @JsonProperty("A2") A2,
    @JsonProperty("B1") B1,
    @JsonProperty("B2") B2,
    @JsonProperty("C1") C1,
    @JsonProperty("C2") C2
}