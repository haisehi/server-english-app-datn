package com.example.english_learning_server.dto;

public class SpeakingCompareRequestDTO {
    private Long lessonId;
    private Long sentenceId;
    private String userText;

    // getters & setters
    public Long getLessonId() { return lessonId; }
    public void setLessonId(Long lessonId) { this.lessonId = lessonId; }

    public Long getSentenceId() { return sentenceId; }
    public void setSentenceId(Long sentenceId) { this.sentenceId = sentenceId; }

    public String getUserText() { return userText; }
    public void setUserText(String userText) { this.userText = userText; }
}
