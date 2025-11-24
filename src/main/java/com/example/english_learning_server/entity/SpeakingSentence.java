package com.example.english_learning_server.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "speaking_sentences")
public class SpeakingSentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private SpeakingLesson lesson;

    @Column(columnDefinition = "TEXT")
    private String sentenceEn;

    private Integer orderIndex;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public SpeakingLesson getLesson() { return lesson; }
    public void setLesson(SpeakingLesson lesson) { this.lesson = lesson; }

    public String getSentenceEn() { return sentenceEn; }
    public void setSentenceEn(String sentenceEn) { this.sentenceEn = sentenceEn; }

    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
}
