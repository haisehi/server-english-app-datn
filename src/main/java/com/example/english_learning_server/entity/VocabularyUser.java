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
@Table(name = "VocabularyUser")
public class VocabularyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khoá chính mới cho bảng VocabularyUser

    @ManyToOne
    @JoinColumn(name = "userId") // Khoá ngoại
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "vocabId") // Khoá ngoại
    @JsonIgnore
    private Vocabulary vocabulary;

    private String progress;

    // Constructor mặc định
    public VocabularyUser() {
    }

    // Constructor có tham số

    public VocabularyUser(Long id, User user, Vocabulary vocabulary, String progress) {
        this.id = id;
        this.user = user;
        this.vocabulary = vocabulary;
        this.progress = progress;
    }


    // Getters và setters (lombok đã tự sinh ra các phương thức này)

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

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
