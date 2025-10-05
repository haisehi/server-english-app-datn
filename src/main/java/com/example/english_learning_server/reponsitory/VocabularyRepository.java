package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Integer> {

    // Tìm Vocabulary theo word
    Optional<Vocabulary> findByWord(String word);

    // Tìm danh sách Vocabulary theo lessonId
    List<Vocabulary> findByLesson_LessonId(Integer lessonId);
}