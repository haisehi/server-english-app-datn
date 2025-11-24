package com.example.english_learning_server.dto;

public class SpeakingLessonDTO {
    private Long topicId;
    private String title;
    private String description;

    // getters and setters
    public Long getTopicId() { return topicId; }
    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
