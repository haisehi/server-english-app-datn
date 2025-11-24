package com.example.english_learning_server.service;
import com.example.english_learning_server.dto.SpeakingSentenceDTO;
import com.example.english_learning_server.dto.SpeakingSentenceResponseDTO;
import com.example.english_learning_server.entity.SpeakingLesson;
import com.example.english_learning_server.entity.SpeakingSentence;
import com.example.english_learning_server.reponsitory.SpeakingLessonRepository;
import com.example.english_learning_server.reponsitory.SpeakingSentenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakingSentenceService {
    private final SpeakingSentenceRepository sentenceRepository;
    private final SpeakingLessonRepository lessonRepository;

    public SpeakingSentenceService(SpeakingSentenceRepository sentenceRepository, SpeakingLessonRepository lessonRepository) {
        this.sentenceRepository = sentenceRepository;
        this.lessonRepository = lessonRepository;
    }

    public SpeakingSentence createSentence(SpeakingSentenceDTO dto) {
        SpeakingLesson lesson = lessonRepository.findById(dto.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        SpeakingSentence sentence = new SpeakingSentence();
        sentence.setLesson(lesson);
        sentence.setSentenceEn(dto.getSentenceEn());
        sentence.setOrderIndex(dto.getOrderIndex());

        return sentenceRepository.save(sentence);
    }

    // ⭐ NEW — Lấy danh sách câu theo bài
    public List<SpeakingSentenceResponseDTO> getSentencesByLesson(Long lessonId) {
        List<SpeakingSentence> sentences =
                sentenceRepository.findByLessonIdOrderByOrderIndexAsc(lessonId);

        return sentences.stream().map(sentence -> {
            SpeakingSentenceResponseDTO dto = new SpeakingSentenceResponseDTO();
            dto.setId(sentence.getId());
            dto.setSentenceEn(sentence.getSentenceEn());
            dto.setOrderIndex(sentence.getOrderIndex());
            return dto;
        }).toList();
    }

    public List<SpeakingSentence> getAllSentences() {
        return sentenceRepository.findAll();
    }
}

