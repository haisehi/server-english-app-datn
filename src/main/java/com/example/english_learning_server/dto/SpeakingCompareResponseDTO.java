package com.example.english_learning_server.dto;

public class SpeakingCompareResponseDTO {
    private boolean isCorrect;
    private double similarity; // 0.0 - 1.0
    private String expected;   // câu chuẩn (original)
    private String feedback;   // message

    // getters & setters
    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }

    public double getSimilarity() { return similarity; }
    public void setSimilarity(double similarity) { this.similarity = similarity; }

    public String getExpected() { return expected; }
    public void setExpected(String expected) { this.expected = expected; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}
