package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.UserTestDTO;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.entity.Test;
import com.example.english_learning_server.entity.UserTest;

public class UserTestConverter {

    public static UserTest dtoToEntity(UserTestDTO dto, User user, Test test) {
        return UserTest.builder()
                .id(dto.getId())
                .user(user)
                .test(test)
                .examTimes(dto.getExamTimes())
                .date(dto.getDate())
                .status(dto.getStatus())
                .score(dto.getScore())
                .answerAttachments(dto.getAnswerAttachments())
                .build();
    }

    public static UserTestDTO entityToDTO(UserTest entity) {
        UserTestDTO dto = new UserTestDTO();
        dto.setId(entity.getId());
        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
        }
        if (entity.getTest() != null) {
            dto.setTestId(entity.getTest().getTestId());
        }
        dto.setExamTimes(entity.getExamTimes());
        dto.setDate(entity.getDate());
        dto.setStatus(entity.getStatus());
        dto.setScore(entity.getScore());
        dto.setAnswerAttachments(entity.getAnswerAttachments());
        return dto;
    }

}