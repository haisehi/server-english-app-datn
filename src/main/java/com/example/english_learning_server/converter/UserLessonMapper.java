package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.UserLessonDTO;
import com.example.english_learning_server.entity.UserLesson;
import org.springframework.stereotype.Component;

@Component
public class UserLessonMapper {

    public UserLessonDTO toDTO(UserLesson userLesson) {
        UserLessonDTO dto = new UserLessonDTO();
        dto.setLessonId(userLesson.getLesson().getLessonId());
        dto.setLessonName(userLesson.getLesson().getLessonName());
        dto.setContent(userLesson.getLesson().getContent());
        dto.setAttachments(userLesson.getLesson().getAttachments());
        dto.setLevel(userLesson.getLesson().getLevel());
        dto.setCourseId(userLesson.getCourse().getCourseId());
        dto.setProgress(userLesson.getProgress());
        return dto;
    }
}