package com.example.english_learning_server.entity;

import com.example.english_learning_server.converter.LocalDateConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@Entity
@Table(name = "UserTest")
public class UserTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "test_id")
    @JsonIgnore
    private Test test;


    private LocalTime examTimes;
    @Convert(converter = LocalDateConverter.class)
    private LocalDate date;
    private Integer status;
    private String score;
    private String answerAttachments;

    @Column(name = "has_participated")
    private Boolean hasParticipated;  // Cột mới để kiểm tra trạng thái tham gia bài test


    // Constructors, Getters, and Setters

    public UserTest() {
    }

    public UserTest(Long id, User user, Test test, LocalTime examTimes, LocalDate date, Integer status, String score, String answerAttachments, Boolean hasParticipated) {
        this.id = id;
        this.user = user;
        this.test = test;
        this.examTimes = examTimes;
        this.date = date;
        this.status = status;
        this.score = score;
        this.answerAttachments = answerAttachments;
        this.hasParticipated = hasParticipated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public LocalTime getExamTimes() {
        return examTimes;
    }

    public void setExamTimes(LocalTime examTimes) {
        this.examTimes = examTimes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAnswerAttachments() {
        return answerAttachments;
    }

    public void setAnswerAttachments(String answerAttachments) {
        this.answerAttachments = answerAttachments;
    }

    public Boolean getHasParticipated() {
        return hasParticipated;
    }

    public void setHasParticipated(Boolean hasParticipated) {
        this.hasParticipated = hasParticipated;
    }
}
