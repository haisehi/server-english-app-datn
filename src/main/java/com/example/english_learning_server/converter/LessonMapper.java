package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.LessonDTO;
import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public Lesson toEntity(LessonDTO dto, Course course) {
        return Lesson.builder()
                .lessonId(dto.getLessonId())
                .lessonName(dto.getLessonName())
                .content(dto.getContent())
                .attachments(dto.getAttachments())
                .level(dto.getLevel())
                .course(course)
                .build();
    }

    public LessonDTO toDTO(Lesson entity) {
        LessonDTO dto = new LessonDTO();
        dto.setLessonId(entity.getLessonId());
        dto.setLessonName(entity.getLessonName());
        dto.setContent(entity.getContent());
        dto.setAttachments(entity.getAttachments());
        dto.setLevel(entity.getLevel());
        if (entity.getCourse() != null) {
            dto.setCourseId(entity.getCourse().getCourseId());
        }
        return dto;
    }
}