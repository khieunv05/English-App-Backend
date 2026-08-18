package com.example.learn_english_app.serviceImp;

import com.example.learn_english_app.dto.response.GeminiWordResponseDto;
import com.example.learn_english_app.entity.WordCache;
import com.example.learn_english_app.repository.WordCacheRepo;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service

public class GeminiService {
    private final Client geminiClient;
    private final ObjectMapper objectMapper;
    private final WordCacheRepo wordCacheRepo;
    private static final Pattern VALID_WORD_PATTERN = Pattern.compile("^[a-zA-Z]{1,30}(?:[ '-][a-zA-Z]{1,30})*$");

    @Value("${gemini.model}")
    private String modelName;

    private void validateWordFormat(String word) {
        if (word == null || word.isBlank()) {
            throw new IllegalArgumentException("Từ không được để trống.");
        }
        if (!VALID_WORD_PATTERN.matcher(word).matches()) {
            throw new IllegalArgumentException("'" + word + "' không đúng định dạng một từ tiếng Anh.");
        }
    }

    public GeminiWordResponseDto generateVocabulary(String rawWord) {
        String word = rawWord.trim().toLowerCase();
        validateWordFormat(word);
        Optional<WordCache> cached = wordCacheRepo.findByWord(word);
        if(cached.isPresent()){
            return toResponse(cached.get());
        }
        GeminiWordResponseDto geminiWordResponse = callGeminiApi(word);
        try {
            WordCache wordCache = new WordCache();
            wordCache.setWord(word);
            wordCache.setPronunciation(geminiWordResponse.getPronunciation());
            wordCache.setVietnamese(geminiWordResponse.getVietnamese());
            wordCache.setExample(geminiWordResponse.getExample());
            wordCache.setExampleTranslation(geminiWordResponse.getExampleTranslation());
            wordCacheRepo.save(wordCache);
        }
        catch (DataIntegrityViolationException e){
            return wordCacheRepo.findByWord(word).map(this::toResponse).orElseThrow();
        }
        return geminiWordResponse;
    }


    private GeminiWordResponseDto callGeminiApi(String rawWord){
        GenerateContentResponse response;
        String jsonOutput;
        GeminiWordResponseDto result;
        String prompt = String.format("""
        Bạn là một chuyên gia ngôn ngữ Anh - Việt.

        Nhiệm vụ của bạn với chuỗi đầu vào: '%s'

        Bước 1 - Kiểm tra tính hợp lệ:
        Xác định xem chuỗi trên có phải là một từ tiếng Anh có thật và có nghĩa hay không
        (không phải chuỗi ký tự ngẫu nhiên, gõ sai chính tả, hoặc vô nghĩa).

        - Nếu KHÔNG hợp lệ: trả về valid = false, các trường còn lại
          (pronunciation, vietnamese, example, exampleTranslation) để chuỗi rỗng "".
          Trường english giữ nguyên chuỗi đầu vào gốc.

        - Nếu hợp lệ: trả về valid = true và tạo đầy đủ dữ liệu từ vựng theo yêu cầu:
          - pronunciation: Phiên âm IPA chuẩn Anh-Mỹ.
          - vietnamese: Dịch nghĩa tiếng Việt ngắn gọn, sát nghĩa nhất.
          - example: Câu ví dụ tự nhiên, đúng ngữ pháp, sử dụng từ '%s'.
          - exampleTranslation: Bản dịch câu ví dụ sang tiếng Việt, chuẩn xác.

        Chỉ trả về JSON theo đúng schema, không thêm giải thích hay văn bản khác.
        """, rawWord, rawWord);

        Schema schema = Schema.builder()
                .type("OBJECT")
                .properties(Map.of(
                        "english", Schema.builder().type("STRING").build(),
                        "pronunciation", Schema.builder().type("STRING").build(),
                        "vietnamese", Schema.builder().type("STRING").build(),
                        "example", Schema.builder().type("STRING").build(),
                        "exampleTranslation", Schema.builder().type("STRING").build(),
                        "valid", Schema.builder().type("BOOLEAN").build()
                ))
                .required(List.of(
                        "english", "pronunciation", "vietnamese", "example", "exampleTranslation", "valid"
                ))
                .build();

        GenerateContentConfig config = GenerateContentConfig.builder()
                .responseMimeType("application/json")
                .responseSchema(schema)
                .temperature(0.1f)
                .build();
        try {
            response = geminiClient.models.generateContent(modelName, prompt, config);
            jsonOutput = response.text();
            result = objectMapper.readValue(jsonOutput, GeminiWordResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi gọi Gemini API: " + e.getMessage(), e);
        }

        if (!result.isValid()) {
            throw new IllegalArgumentException("'" + rawWord + "' không phải là một từ tiếng Anh hợp lệ.");
        }

        return result;
    }
    private GeminiWordResponseDto toResponse(WordCache wordCache){
        GeminiWordResponseDto geminiWordResponse = new GeminiWordResponseDto();
        geminiWordResponse.setEnglish(wordCache.getWord());
        geminiWordResponse.setVietnamese(wordCache.getVietnamese());
        geminiWordResponse.setExample(wordCache.getExample());
        geminiWordResponse.setPronunciation(wordCache.getPronunciation());
        geminiWordResponse.setExampleTranslation(wordCache.getExampleTranslation());
        geminiWordResponse.setValid(true);
        return geminiWordResponse;
    }
}