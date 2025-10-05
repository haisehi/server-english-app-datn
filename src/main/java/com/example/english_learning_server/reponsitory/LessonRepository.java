package com.example.english_learning_server.reponsitory;


import com.example.english_learning_server.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findByCourseCourseId(Integer courseId);
    List<Lesson> findByLevel(int level);

}