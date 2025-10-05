package com.example.english_learning_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "UserAnswer")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính riêng biệt

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user; // Khóa phụ (foreign key)

    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    @JsonIgnore
    private Test test; // Khóa phụ (foreign key)

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    @JsonIgnore
    private Question question; // Khóa phụ (foreign key)

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    @JsonIgnore
    private AnswerOption option; // Khóa phụ (foreign key)

    private String score; // Thông tin thêm

    // Các constructor và phương thức getter/setter nếu cần

    public UserAnswer() {
    }

    public UserAnswer(Long id, User user, Test test, Question question, AnswerOption option, String score) {
        this.id = id;
        this.user = user;
        this.test = test;
        this.question = question;
        this.option = option;
        this.score = score;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public AnswerOption getOption() {
        return option;
    }

    public void setOption(AnswerOption option) {
        this.option = option;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
