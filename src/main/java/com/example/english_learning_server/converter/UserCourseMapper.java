package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.UserCourseDTO;
import com.example.english_learning_server.entity.UserCourse;
import org.springframework.stereotype.Component;

@Component
public class UserCourseMapper {

    public UserCourseDTO toDto(UserCourse userCourse) {
        if (userCourse == null) {
            return null;
        }

        return UserCourseDTO.builder()
                .id(userCourse.getId())
                .studentCode(userCourse.getStudentCode())
                .role(userCourse.getRole().name())
                .status(userCourse.getStatus())
                .courseId(userCourse.getCourse().getCourseId())
                .courseName(userCourse.getCourse().getCourseName())
                .statusCourse(userCourse.getCourse().getStatusCourse())
                .courseCode(userCourse.getCourse().getCourseCode())
                .maxQuantity(userCourse.getCourse().getMaxQuantity())
                .image(userCourse.getCourse().getImage())

                .build();
    }
}