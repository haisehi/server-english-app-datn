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
@Table(name = "Lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lessonId;

    private String lessonName;
    private String content;
    private String attachments;
    private int level;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<UserLesson> userLessons = new ArrayList<>();

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Vocabulary> vocabularies = new ArrayList<>();

    // Getters and setters


    public Lesson() {
    }

    public Lesson(Integer lessonId, String lessonName, String content, String attachments, int level, Course course, List<UserLesson> userLessons, List<Vocabulary> vocabularies) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.content = content;
        this.attachments = attachments;
        this.level = level;
        this.course = course;
        this.userLessons = userLessons;
        this.vocabularies = vocabularies;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<UserLesson> getUserLessons() {
        return userLessons;
    }

    public void setUserLessons(List<UserLesson> userLessons) {
        this.userLessons = userLessons;
    }

    public List<Vocabulary> getVocabularies() {
        return vocabularies;
    }

    public void setVocabularies(List<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }
}
