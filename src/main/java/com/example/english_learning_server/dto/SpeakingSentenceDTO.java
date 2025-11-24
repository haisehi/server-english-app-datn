package com.example.english_learning_server.dto;

public class SpeakingSentenceDTO {
    private Long lessonId;
    private String sentenceEn;
    private Integer orderIndex;

    // getters and setters
    public Long getLessonId() { return lessonId; }
    public void setLessonId(Long lessonId) { this.lessonId = lessonId; }

    public String getSentenceEn() { return sentenceEn; }
    public void setSentenceEn(String sentenceEn) { this.sentenceEn = sentenceEn; }

    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
}
