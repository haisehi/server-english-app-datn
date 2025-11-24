package com.example.english_learning_server.dto;

public class SpeakingUserScoreDTO {
    private Long userId;
    private Long lessonId;
    private Integer totalCorrect;
    private Integer totalSentences;
    private Boolean isPass;

    // getters and setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getLessonId() { return lessonId; }
    public void setLessonId(Long lessonId) { this.lessonId = lessonId; }

    public Integer getTotalCorrect() { return totalCorrect; }
    public void setTotalCorrect(Integer totalCorrect) { this.totalCorrect = totalCorrect; }

    public Integer getTotalSentences() { return totalSentences; }
    public void setTotalSentences(Integer totalSentences) { this.totalSentences = totalSentences; }

    public Boolean getIsPass() { return isPass; }
    public void setIsPass(Boolean isPass) { this.isPass = isPass; }
}
