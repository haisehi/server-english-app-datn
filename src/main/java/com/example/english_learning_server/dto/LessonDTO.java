package com.example.english_learning_server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LessonDTO {
    private Integer lessonId;
    private String lessonName;
    private String content;
    private String attachments;
    private int level;
    @JsonProperty("courseId")
    private Integer courseId; // Để lưu thông tin course liên kết
    private Double progress;

}