package com.example.english_learning_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Integer courseId;
    private String courseName;
    private String courseCode;
    private Long maxQuantity;
    private Integer status;
    private String image;
}