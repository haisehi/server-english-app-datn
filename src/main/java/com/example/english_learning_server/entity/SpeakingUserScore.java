package com.example.english_learning_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "speaking_user_scores")
public class SpeakingUserScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Liên kết đúng với bảng User
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // Liên kết đúng với SpeakingLesson
    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private SpeakingLesson lesson;

    @Column(name = "total_correct")
    private Integer totalCorrect;

    @Column(name = "total_sentences")
    private Integer totalSentences;

    @Column(name = "is_pass")
    private Boolean isPass;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // GETTER & SETTER
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public SpeakingLesson getLesson() { return lesson; }
    public void setLesson(SpeakingLesson lesson) { this.lesson = lesson; }

    public Integer getTotalCorrect() { return totalCorrect; }
    public void setTotalCorrect(Integer totalCorrect) { this.totalCorrect = totalCorrect; }

    public Integer getTotalSentences() { return totalSentences; }
    public void setTotalSentences(Integer totalSentences) { this.totalSentences = totalSentences; }

    public Boolean getIsPass() { return isPass; }
    public void setIsPass(Boolean pass) { isPass = pass; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
