package com.example.english_learning_server.service;


import com.example.english_learning_server.dto.SpeakingTopicDTO;
import com.example.english_learning_server.entity.SpeakingTopic;
import com.example.english_learning_server.reponsitory.SpeakingTopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakingTopicService {
    private final SpeakingTopicRepository repository;

    public SpeakingTopicService(SpeakingTopicRepository repository) {
        this.repository = repository;
    }

    public SpeakingTopic createTopic(SpeakingTopicDTO dto) {
        SpeakingTopic topic = new SpeakingTopic();
        topic.setTitle(dto.getTitle());
        topic.setDescription(dto.getDescription());
        return repository.save(topic);
    }

    public List<SpeakingTopic> getAllTopics() {
        return repository.findAll();
    }
}
