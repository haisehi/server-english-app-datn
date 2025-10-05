package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.AnswerOptionDTO;
import com.example.english_learning_server.entity.AnswerOption;
import com.example.english_learning_server.entity.Question;
import org.springframework.stereotype.Component;

@Component
public class AnswerOptionMapper {

    public AnswerOptionDTO toDTO(AnswerOption answerOption) {
        AnswerOptionDTO dto = new AnswerOptionDTO();
        dto.setAnswerId(answerOption.getAnswerId());
        dto.setCorrectAnswer(answerOption.getCorrectAnswer());
        dto.setContent(answerOption.getContent());
        dto.setAttachments(answerOption.getAttachments());
        dto.setId(answerOption.getQuestion() != null ? answerOption.getQuestion().getId() : null);
        return dto;
    }

    public AnswerOption toEntity(AnswerOptionDTO dto) {
        AnswerOption answerOption = new AnswerOption();
        answerOption.setAnswerId(dto.getAnswerId());
        answerOption.setCorrectAnswer(dto.getCorrectAnswer());
        answerOption.setContent(dto.getContent());
        answerOption.setAttachments(dto.getAttachments());

        // Map Question ID to Question entity
        if (dto.getId() != null) {
            Question question = new Question();
            question.setId(dto.getId());
            answerOption.setQuestion(question);
        }

        return answerOption;
    }
}
