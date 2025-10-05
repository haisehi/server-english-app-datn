package com.example.english_learning_server.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private Integer id;
    private String content;
    private String attachments;
    private String type;
    private String score;
    private Integer testId;
    private List<AnswerOptionDTO> answerOptions;
}
