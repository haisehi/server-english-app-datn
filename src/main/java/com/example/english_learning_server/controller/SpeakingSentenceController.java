package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.SpeakingSentenceDTO;
import com.example.english_learning_server.dto.SpeakingSentenceResponseDTO;
import com.example.english_learning_server.entity.SpeakingSentence;
import com.example.english_learning_server.service.SpeakingSentenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sentences")
public class SpeakingSentenceController {
    private final SpeakingSentenceService service;

    public SpeakingSentenceController(SpeakingSentenceService service) {
        this.service = service;
    }

    @PostMapping
    public SpeakingSentence createSentence(@RequestBody SpeakingSentenceDTO dto) {
        return service.createSentence(dto);
    }

    @GetMapping
    public List<SpeakingSentence> getAllSentences() {
        return service.getAllSentences();
    }

    // API lấy câu theo bài nói
    @GetMapping("/by-lesson/{lessonId}")
    public List<SpeakingSentenceResponseDTO> getSentencesByLesson(@PathVariable Long lessonId) {
        return service.getSentencesByLesson(lessonId);
    }

}
