package com.example.english_learning_server.reponsitory;


import com.example.english_learning_server.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByTest_TestId(Integer testId);
}