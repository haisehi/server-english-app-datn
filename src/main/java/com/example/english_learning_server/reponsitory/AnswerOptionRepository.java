package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Integer> {
    // Tìm tất cả AnswerOption của một Question
    List<AnswerOption> findByQuestion_Id(Integer questionId);
}