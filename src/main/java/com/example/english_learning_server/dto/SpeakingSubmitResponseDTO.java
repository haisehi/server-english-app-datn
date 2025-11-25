package com.example.english_learning_server.dto;

import java.time.LocalDateTime;

public class SpeakingSubmitResponseDTO {
    private Long id;
    private Long lessonId;
    private Integer userId;
    private Integer totalCorrect;
    private Integer totalSentences;
    private Boolean isPass;
    private LocalDateTime createdAt;
    private Integer percent;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLessonId() { return lessonId; }
    public void setLessonId(Long lessonId) { this.lessonId = lessonId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getTotalCorrect() { return totalCorrect; }
    public void setTotalCorrect(Integer totalCorrect) { this.totalCorrect = totalCorrect; }

    public Integer getTotalSentences() { return totalSentences; }
    public void setTotalSentences(Integer totalSentences) { this.totalSentences = totalSentences; }

    public Boolean getIsPass() { return isPass; }
    public void setIsPass(Boolean isPass) { this.isPass = isPass; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Integer getPercent() { return percent; }
    public void setPercent(Integer percent) { this.percent = percent; }
}
