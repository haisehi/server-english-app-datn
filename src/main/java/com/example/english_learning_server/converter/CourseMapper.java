package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.CourseDTO;
import com.example.english_learning_server.entity.Course;

public class CourseMapper {
    public static CourseDTO toDTO(Course course) {
        return new CourseDTO(
                course.getCourseId(),
                course.getCourseName(),
                course.getCourseCode(),
                course.getMaxQuantity(),
                course.getStatusCourse(),
                course.getImage()
        );
    }
}