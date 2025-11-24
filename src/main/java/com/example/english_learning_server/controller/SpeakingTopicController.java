package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.SpeakingTopicDTO;
import com.example.english_learning_server.entity.SpeakingTopic;
import com.example.english_learning_server.service.SpeakingTopicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
public class SpeakingTopicController {
    private final SpeakingTopicService service;

    public SpeakingTopicController(SpeakingTopicService service) {
        this.service = service;
    }

    @PostMapping
    public SpeakingTopic createTopic(@RequestBody SpeakingTopicDTO dto) {
        return service.createTopic(dto);
    }

    @GetMapping
    public List<SpeakingTopic> getAllTopics() {
        return service.getAllTopics();
    }
}
