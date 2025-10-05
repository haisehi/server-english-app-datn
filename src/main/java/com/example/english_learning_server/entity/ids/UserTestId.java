//package com.example.english_learning_server.entity.ids;
//
//import jakarta.persistence.Embeddable;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//public class UserTestId implements Serializable {
//
//    private Integer userId;
//    private Integer testId;
//
//    public UserTestId() {
//    }
//
//    public UserTestId(Integer userId, Integer testId) {
//        this.userId = userId;
//        this.testId = testId;
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
//    public Integer getTestId() {
//        return testId;
//    }
//
//    public void setTestId(Integer testId) {
//        this.testId = testId;
//    }
//
//    // Override equals and hashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserTestId that = (UserTestId) o;
//        return Objects.equals(userId, that.userId) &&
//                Objects.equals(testId, that.testId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, testId);
//    }
//}
