package com.example.english_learning_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_lesson")
@JsonIgnoreProperties({"user", "course", "lesson"}) // Thêm annotation để tránh xuất ra vòng lặp
public class UserLesson {

    // Thêm khóa chính mới (id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private Lesson lesson;

    @Column(name = "Progress")
    private double progress;

    @Column(name = "status")
    private Integer status;

    public UserLesson() {
    }

    public UserLesson(Long id, User user, Course course, Lesson lesson, double progress, Integer status) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.lesson = lesson;
        this.progress = progress;
        this.status = status;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}
