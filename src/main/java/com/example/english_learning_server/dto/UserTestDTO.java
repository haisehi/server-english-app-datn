package com.example.english_learning_server.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class UserTestDTO {
    private Long id; // ID của UserTest
    private Integer userId; // ID của User
    private Integer testId; // ID của Test
    private LocalTime examTimes;
    private LocalDate date;
    private Integer status;
    private String score;
    private String answerAttachments;
}
