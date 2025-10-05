//package com.example.english_learning_server.entity.ids;
//
//import jakarta.persistence.Embeddable;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Embeddable
//public class VocabularyUserId implements Serializable {
//
//    private Integer userId;
//    private Integer topicId;
//    private Integer vocabId;
//
//    public VocabularyUserId() {
//    }
//
//    public VocabularyUserId(Integer userId, Integer topicId, Integer vocabId) {
//        this.userId = userId;
//        this.topicId = topicId;
//        this.vocabId = vocabId;
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
//    public Integer getTopicId() {
//        return topicId;
//    }
//
//    public void setTopicId(Integer topicId) {
//        this.topicId = topicId;
//    }
//
//    public Integer getVocabId() {
//        return vocabId;
//    }
//
//    public void setVocabId(Integer vocabId) {
//        this.vocabId = vocabId;
//    }
//
//    // Override equals and hashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        VocabularyUserId that = (VocabularyUserId) o;
//        return Objects.equals(userId, that.userId) &&
//                Objects.equals(topicId, that.topicId) &&
//                Objects.equals(vocabId, that.vocabId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, topicId, vocabId);
//    }
//}
