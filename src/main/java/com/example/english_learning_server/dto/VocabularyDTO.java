package com.example.english_learning_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyDTO {

    private Integer vocabId;
    private String word;
    private String meaning;
    private String description;
    private String image;
    private String audio;
    private Integer lessonId;

}
