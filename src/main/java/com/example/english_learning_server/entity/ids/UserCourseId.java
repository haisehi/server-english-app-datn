//package com.example.english_learning_server.entity.ids;
//
//import jakarta.persistence.Embeddable;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//public class UserCourseId implements Serializable {
//
//    private Integer userId;
//    private Integer courseId;
//
//    public UserCourseId() {
//    }
//
//    public UserCourseId(Integer userId, Integer courseId) {
//        this.userId = userId;
//        this.courseId = courseId;
//    }
//
//    // Getters and Setters
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public Integer getCourseId() {
//        return courseId;
//    }
//
//    public void setCourseId(Integer courseId) {
//        this.courseId = courseId;
//    }
//
//    // Override equals and hashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserCourseId that = (UserCourseId) o;
//        return Objects.equals(userId, that.userId) &&
//                Objects.equals(courseId, that.courseId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, courseId);
//    }
//}
