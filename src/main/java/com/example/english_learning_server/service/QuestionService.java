package com.example.english_learning_server.service;


import com.example.english_learning_server.converter.QuestionMapper;
import com.example.english_learning_server.dto.QuestionDTO;
import com.example.english_learning_server.entity.Question;
import com.example.english_learning_server.reponsitory.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    public QuestionDTO addQuestion(QuestionDTO questionDTO) {
        Question question = questionMapper.toEntity(questionDTO);
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toDTO(savedQuestion);
    }

    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<QuestionDTO> getQuestionsByTestId(Integer testId) {
        List<Question> questions = questionRepository.findByTest_TestId(testId);
        return questions.stream()
                .map(questionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public QuestionDTO updateQuestion(Integer id, QuestionDTO questionDTO) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        existingQuestion.setContent(questionDTO.getContent());
        existingQuestion.setAttachments(questionDTO.getAttachments());
        existingQuestion.setType(questionDTO.getType());
        existingQuestion.setScore(questionDTO.getScore());
        Question updatedQuestion = questionRepository.save(existingQuestion);
        return questionMapper.toDTO(updatedQuestion);
    }

    public void deleteQuestion(Integer id) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        questionRepository.delete(existingQuestion);
    }
}