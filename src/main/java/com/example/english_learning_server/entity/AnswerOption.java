package com.example.english_learning_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "AnswerOption")
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    private String correctAnswer;
    private String content;
    private String attachments;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    private List<UserAnswer> userAnswers = new ArrayList<>();

    // Getters and setters

    public AnswerOption() {

    }

    public AnswerOption(Integer answerId, String correctAnswer, String content, String attachments, Question question, List<UserAnswer> userAnswers) {
        this.answerId = answerId;
        this.correctAnswer = correctAnswer;
        this.content = content;
        this.attachments = attachments;
        this.question = question;
        this.userAnswers = userAnswers;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
