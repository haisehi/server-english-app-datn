//package com.example.english_learning_server.entity.ids;
//
//import jakarta.persistence.Embeddable;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//public class UserAnswerId implements Serializable {
//
//    private Integer userId;
//    private Integer testId;
//    private Integer questionId;
//    private Integer optionId;  // Thêm thuộc tính optionId
//
//    public UserAnswerId() {
//    }
//
//    public UserAnswerId(Integer userId, Integer testId, Integer questionId, Integer optionId) {
//        this.userId = userId;
//        this.testId = testId;
//        this.questionId = questionId;
//        this.optionId = optionId;  // Khởi tạo optionId
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
//    public Integer getQuestionId() {
//        return questionId;
//    }
//
//    public void setQuestionId(Integer questionId) {
//        this.questionId = questionId;
//    }
//
//    public Integer getOptionId() {
//        return optionId;
//    }
//
//    public void setOptionId(Integer optionId) {
//        this.optionId = optionId;
//    }
//
//    // Override equals and hashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserAnswerId that = (UserAnswerId) o;
//        return Objects.equals(userId, that.userId) &&
//                Objects.equals(testId, that.testId) &&
//                Objects.equals(questionId, that.questionId) &&
//                Objects.equals(optionId, that.optionId);  // Cập nhật phương thức equals
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, testId, questionId, optionId);  // Cập nhật phương thức hashCode
//    }
//}
