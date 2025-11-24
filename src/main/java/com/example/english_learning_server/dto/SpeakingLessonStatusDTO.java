package com.example.english_learning_server.dto;

public class SpeakingLessonStatusDTO {
    private Long lessonId;
    private String title;
    private String description;
    private Boolean joined;   // true nếu user đã làm, false nếu chưa
    private Integer totalCorrect;
    private Integer totalSentences;
    private Boolean isPass;

    // getters & setters

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getJoined() {
        return joined;
    }

    public void setJoined(Boolean joined) {
        this.joined = joined;
    }

    public Integer getTotalCorrect() {
        return totalCorrect;
    }

    public void setTotalCorrect(Integer totalCorrect) {
        this.totalCorrect = totalCorrect;
    }

    public Integer getTotalSentences() {
        return totalSentences;
    }

    public void setTotalSentences(Integer totalSentences) {
        this.totalSentences = totalSentences;
    }

    public Boolean getPass() {
        return isPass;
    }

    public void setPass(Boolean pass) {
        isPass = pass;
    }
}
