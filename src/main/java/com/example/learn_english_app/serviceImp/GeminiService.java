package com.example.learn_english_app.serviceImp;

import com.example.learn_english_app.dto.response.GeminiPhraseResponseDto;
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

    public GeminiPhraseResponseDto callGeminiPhrase(String inputText){
        String prompt = String.format("""
        Bạn là một giáo viên tiếng Anh chuyên chấm chữa lỗi ngữ pháp,
        có nhiều năm kinh nghiệm giảng dạy cho người Việt học tiếng Anh.

        Hãy phân tích đoạn văn bản tiếng Anh sau:
        '%s'

        Thực hiện theo đúng thứ tự các bước sau:

        BƯỚC 1 - Sửa lỗi:
        Đọc kỹ toàn bộ đoạn văn, xác định NGỮ CẢNH chung (thì của câu, chủ đề,
        văn phong) trước khi sửa từng lỗi riêng lẻ. Điều này đảm bảo các lỗi
        được sửa nhất quán với nhau (ví dụ: nếu cả đoạn ở thì quá khứ, mọi lỗi
        chia động từ phải được sửa về đúng thì quá khứ, không phải thì hiện tại).

        BƯỚC 2 - Điền kết quả theo cấu trúc:
        1. text: giữ nguyên văn bản gốc người dùng nhập vào, không thay đổi.
        2. correctedText: toàn bộ văn bản sau khi đã sửa hết lỗi, giữ nguyên
           ý nghĩa và văn phong gốc, chỉ sửa phần sai.
        3. grammarErrors: liệt kê TẤT CẢ lỗi tìm được ở Bước 1, mỗi lỗi gồm:
           - incorrect: đoạn văn bản gốc bị sai (trích chính xác từ câu gốc).
           - correction: cách sửa đúng cho đoạn đó.
           - explanation: giải thích ngắn gọn bằng tiếng Việt tại sao đó là lỗi
             và tại sao cách sửa lại đúng.
           Nếu văn bản không có lỗi nào, trả về mảng rỗng [].
        4. score: chấm điểm từ 0 đến 10 dựa trên độ chính xác ngữ pháp,
           cách dùng từ, và độ tự nhiên của câu. 10 là hoàn hảo không lỗi,
           càng nhiều lỗi nghiêm trọng thì điểm càng thấp.

        BƯỚC 3 - Tự kiểm tra trước khi trả kết quả:
        Đảm bảo mỗi "correction" trong grammarErrors, khi áp dụng vào đúng vị trí
        tương ứng trong "text", phải cho ra kết quả khớp CHÍNH XÁC với "correctedText".
        Nếu phát hiện không khớp, sửa lại "correction" hoặc "correctedText" cho nhất quán
        trước khi trả về.

        Chỉ trả về JSON theo đúng schema, không thêm giải thích hay văn bản nào khác
        ngoài JSON.
        """, inputText);

        Schema grammarErrorSchema = Schema.builder()
                .type("OBJECT")
                .properties(Map.of(
                        "incorrect", Schema.builder().type("STRING").build(),
                        "correction", Schema.builder().type("STRING").build(),
                        "explanation", Schema.builder().type("STRING").build()
                ))
                .required(List.of("incorrect", "correction", "explanation"))
                .build();

        Schema schema = Schema.builder()
                .type("OBJECT")
                .properties(Map.of(
                        "text", Schema.builder().type("STRING").build(),
                        "score", Schema.builder().type("INTEGER").build(),
                        "grammarErrors", Schema.builder()
                                .type("ARRAY")
                                .items(grammarErrorSchema)
                                .build(),
                        "correctedText", Schema.builder().type("STRING").build()
                ))
                .required(List.of("text", "score", "grammarErrors", "correctedText"))
                .build();

        GenerateContentConfig config = GenerateContentConfig.builder()
                .responseMimeType("application/json")
                .responseSchema(schema)
                .temperature(0.1f)
                .build();
        try{
            GenerateContentResponse response = geminiClient.models.generateContent(
                    modelName,
                    prompt,
                    config
            );

            String jsonOutput = response.text();
            return objectMapper.readValue(jsonOutput, GeminiPhraseResponseDto.class);

        }
        catch (Exception e){
            throw new RuntimeException("Gặp lỗi khi gọi Gemini API: " + e.getMessage(),e);
        }
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