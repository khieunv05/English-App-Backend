package com.example.learn_english_app.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorResponse {
    private String timestamp = LocalDateTime.now().toString();
    private String messages;
    private Map<String,String> error;

    public ErrorResponse(String messages) {
        this.messages = messages;
        error = null;
    }

    public ErrorResponse(String messages, Map<String, String> error) {
        this.messages = messages;
        this.error = error;
    }
}
