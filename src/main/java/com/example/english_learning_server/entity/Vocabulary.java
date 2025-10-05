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
@Table(name = "Vocabulary")
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vocabId;

    private String word;
    private String meaning;
    private String description;
    private String image;
    private String audio;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private Lesson lesson;

    // Thêm quan hệ với VocabularyUser
    @OneToMany(mappedBy = "vocabulary", cascade = CascadeType.ALL)
    private List<VocabularyUser> vocabularyUsers = new ArrayList<>();


    // Getters and setters

    public Vocabulary() {
    }

    public Vocabulary(Integer vocabId, String word, String meaning, String description, String image, String audio, Lesson lesson, List<VocabularyUser> vocabularyUsers) {
        this.vocabId = vocabId;
        this.word = word;
        this.meaning = meaning;
        this.description = description;
        this.image = image;
        this.audio = audio;
        this.lesson = lesson;
        this.vocabularyUsers = vocabularyUsers;
    }

    public Integer getVocabId() {
        return vocabId;
    }

    public void setVocabId(Integer vocabId) {
        this.vocabId = vocabId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public List<VocabularyUser> getVocabularyUsers() {
        return vocabularyUsers;
    }

    public void setVocabularyUsers(List<VocabularyUser> vocabularyUsers) {
        this.vocabularyUsers = vocabularyUsers;
    }
}
