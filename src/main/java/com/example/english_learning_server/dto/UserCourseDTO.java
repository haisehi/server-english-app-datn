package com.example.english_learning_server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseDTO {
    private Long id;
    private Integer studentCode;
    private String role;
    private Integer status;
    private Integer courseId;
    private String courseName;
    private String courseCode;
    private Integer statusCourse;
    private Long maxQuantity;
    private String image;
    private String enrollmentStatus;


}