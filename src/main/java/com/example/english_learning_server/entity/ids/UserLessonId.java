//package com.example.english_learning_server.entity.ids;
//
//import jakarta.persistence.Embeddable;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//public class UserLessonId implements Serializable {
//
//    private Integer userId;
//    private Integer courseId;
//    private Integer lessonId;
//
//    // Getters and setters
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
//    public Integer getLessonId() {
//        return lessonId;
//    }
//
//    public void setLessonId(Integer lessonId) {
//        this.lessonId = lessonId;
//    }
//
//    // Override equals() and hashCode() methods for composite keys
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserLessonId that = (UserLessonId) o;
//        return Objects.equals(userId, that.userId) &&
//                Objects.equals(courseId, that.courseId) &&
//                Objects.equals(lessonId, that.lessonId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, courseId, lessonId);
//    }
//}
