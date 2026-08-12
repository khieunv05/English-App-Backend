package com.example.learn_english_app.serviceImp;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class SrsService {
    private static final int[] INTERVAL_DAYS = {1, 3, 7, 14, 30};
    public LocalDateTime calculateNextReview(int reviewCount) {
        int index = Math.min(reviewCount - 1, INTERVAL_DAYS.length - 1);
        int days = INTERVAL_DAYS[index];
        return LocalDateTime.now().plusDays(days);
    }
}
