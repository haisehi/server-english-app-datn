package com.example.english_learning_server.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TestDTO {
    private Integer testId;
    private String testName;
    private String description;
    private Integer examTime;  // Chuyển kiểu dữ liệu sang Integer
    private LocalDate examDate;
    private String type;
    private String maxNumberOfExams;
    private String level;
    private String passingScore;
    private Integer status;
    private Integer courseId;
}
