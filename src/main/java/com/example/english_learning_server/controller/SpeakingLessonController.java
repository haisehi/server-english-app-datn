package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.SpeakingLessonDTO;
import com.example.english_learning_server.dto.SpeakingLessonStatusDTO;
import com.example.english_learning_server.entity.SpeakingLesson;
import com.example.english_learning_server.service.SpeakingLessonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/SpeakingLessons")
public class SpeakingLessonController {
    private final SpeakingLessonService service;

    public SpeakingLessonController(SpeakingLessonService service) {
        this.service = service;
    }

    @PostMapping
    public SpeakingLesson createLesson(@RequestBody SpeakingLessonDTO dto) {
        return service.createLesson(dto);
    }

    @GetMapping
    public List<SpeakingLesson> getAllLessons() {
        return service.getAllLessons();
    }

    @GetMapping("/TopicId/{topicId}/me")
    public List<SpeakingLessonStatusDTO> getLessonsByTopicForCurrentUser(@PathVariable Long topicId) {
        return service.getLessonsByTopicForCurrentUser(topicId);
    }

}
