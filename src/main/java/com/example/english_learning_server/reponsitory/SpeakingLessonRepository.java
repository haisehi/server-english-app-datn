package com.example.english_learning_server.reponsitory;


import com.example.english_learning_server.entity.SpeakingLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeakingLessonRepository extends JpaRepository<SpeakingLesson, Long> {
    List<SpeakingLesson> findByTopicId(Long topicId);
}
