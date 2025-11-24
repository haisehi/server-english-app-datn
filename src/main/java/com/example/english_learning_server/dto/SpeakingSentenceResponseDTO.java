package com.example.english_learning_server.dto;

public class SpeakingSentenceResponseDTO {
    private Long id;
    private String sentenceEn;
    private Integer orderIndex;

    // GETTER & SETTER
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSentenceEn() { return sentenceEn; }
    public void setSentenceEn(String sentenceEn) { this.sentenceEn = sentenceEn; }

    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
}
