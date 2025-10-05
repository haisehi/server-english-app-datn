package com.example.english_learning_server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyUserDTO {
    private Long id;
    private Integer userId;
    private Integer vocabId;
    private String progress;
    // Thêm trường word và meaning từ bảng vocabulary
    private String word;
    private String meaning;
}