package com.example.english_learning_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    private String courseName;
    private String courseCode;
    private Long maxQuantity;
    private Integer statusCourse;
    private String image;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<UserCourse> userCourses = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<UserLesson> userLessons = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Lesson> lessons = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Test> tests = new ArrayList<>();

    // Getters and setters


    public Course() {
    }

    public Course(Integer courseId, String courseName, String courseCode, Long maxQuantity, Integer statusCourse, String image, List<UserCourse> userCourses, List<UserLesson> userLessons, List<Lesson> lessons, List<Test> tests) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.maxQuantity = maxQuantity;
        this.statusCourse = statusCourse;
        this.image = image;
        this.userCourses = userCourses;
        this.userLessons = userLessons;
        this.lessons = lessons;
        this.tests = tests;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Long getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Long maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Integer getStatusCourse() {
        return statusCourse;
    }

    public void setStatusCourse(Integer statusCourse) {
        this.statusCourse = statusCourse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<UserCourse> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(List<UserCourse> userCourses) {
        this.userCourses = userCourses;
    }

    public List<UserLesson> getUserLessons() {
        return userLessons;
    }

    public void setUserLessons(List<UserLesson> userLessons) {
        this.userLessons = userLessons;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
