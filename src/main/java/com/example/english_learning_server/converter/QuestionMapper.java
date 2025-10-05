package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.QuestionDTO;
import com.example.english_learning_server.entity.Question;
import com.example.english_learning_server.entity.Test;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    public QuestionDTO toDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setContent(question.getContent());
        dto.setAttachments(question.getAttachments());
        dto.setType(question.getType());
        dto.setScore(question.getScore());
        dto.setTestId(question.getTest() != null ? question.getTest().getTestId() : null);

        dto.setAnswerOptions(question.getAnswerOptions().stream()
                .map(answerOption -> new AnswerOptionMapper().toDTO(answerOption))
                .collect(Collectors.toList()));
        return dto;
    }

    public Question toEntity(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setContent(questionDTO.getContent());
        question.setAttachments(questionDTO.getAttachments());
        question.setType(questionDTO.getType());
        question.setScore(questionDTO.getScore());

        // Map testId to Test entity
        if (questionDTO.getTestId() != null) {
            Test test = new Test();
            test.setTestId(questionDTO.getTestId());
            question.setTest(test);
        }

        // Map AnswerOptionDTOs to AnswerOptions
        if (questionDTO.getAnswerOptions() != null) {
            question.setAnswerOptions(questionDTO.getAnswerOptions().stream()
                    .map(answerOptionDTO -> new AnswerOptionMapper().toEntity(answerOptionDTO))
                    .collect(Collectors.toList()));
        }

        return question;
    }
}