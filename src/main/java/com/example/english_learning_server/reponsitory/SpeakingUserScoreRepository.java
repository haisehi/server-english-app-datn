package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.SpeakingUserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeakingUserScoreRepository extends JpaRepository<SpeakingUserScore, Long> {

    @Query("SELECT s FROM SpeakingUserScore s WHERE s.user.id = :userId AND s.lesson.id = :lessonId")
    Optional<SpeakingUserScore> findByUserAndLesson(Integer userId, Long lessonId);
}
