package com.example.english_learning_server.reponsitory;


import com.example.english_learning_server.entity.SpeakingTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakingTopicRepository extends JpaRepository<SpeakingTopic, Long> {
}