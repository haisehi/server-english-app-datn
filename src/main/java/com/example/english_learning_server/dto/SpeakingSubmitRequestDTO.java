package com.example.english_learning_server.dto;

import java.util.List;

public class SpeakingSubmitRequestDTO {
    private Long lessonId;
    private List<SpeakingResultItemDTO> results;

    // getters/setters
    public Long getLessonId() { return lessonId; }
    public void setLessonId(Long lessonId) { this.lessonId = lessonId; }

    public List<SpeakingResultItemDTO> getResults() { return results; }
    public void setResults(List<SpeakingResultItemDTO> results) { this.results = results; }

    public static class SpeakingResultItemDTO {
        private Long sentenceId;
        private String userText;

        // getters/setters
        public Long getSentenceId() { return sentenceId; }
        public void setSentenceId(Long sentenceId) { this.sentenceId = sentenceId; }

        public String getUserText() { return userText; }
        public void setUserText(String userText) { this.userText = userText; }
    }
}
