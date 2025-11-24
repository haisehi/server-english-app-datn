package com.example.english_learning_server.reponsitory;


import com.example.english_learning_server.entity.SpeakingSentence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeakingSentenceRepository extends JpaRepository<SpeakingSentence, Long> {
    List<SpeakingSentence> findByLessonIdOrderByOrderIndexAsc(Long lessonId);
}
