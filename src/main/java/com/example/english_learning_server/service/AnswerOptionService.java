package com.example.english_learning_server.service;

import com.example.english_learning_server.converter.AnswerOptionMapper;
import com.example.english_learning_server.dto.AnswerOptionDTO;
import com.example.english_learning_server.entity.AnswerOption;
import com.example.english_learning_server.reponsitory.AnswerOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerOptionService {

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @Autowired
    private AnswerOptionMapper answerOptionMapper;

    // Thêm AnswerOption mới
    public AnswerOptionDTO addAnswerOption(AnswerOptionDTO answerOptionDTO) {
        AnswerOption answerOption = answerOptionMapper.toEntity(answerOptionDTO);
        AnswerOption savedAnswerOption = answerOptionRepository.save(answerOption);
        return answerOptionMapper.toDTO(savedAnswerOption);
    }

    // Hiện tất cả AnswerOptions
    public List<AnswerOptionDTO> getAllAnswerOptions() {
        List<AnswerOption> answerOptions = answerOptionRepository.findAll();
        return answerOptions.stream()
                .map(answerOptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Hiện AnswerOptions theo ID câu hỏi (questionId)
    public List<AnswerOptionDTO> getAnswerOptionsByQuestionId(Integer questionId) {
        List<AnswerOption> answerOptions = answerOptionRepository.findByQuestion_Id(questionId);
        return answerOptions.stream()
                .map(answerOptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Cập nhật AnswerOption theo ID
    public AnswerOptionDTO updateAnswerOption(Integer answerId, AnswerOptionDTO answerOptionDTO) {
        Optional<AnswerOption> existingAnswerOption = answerOptionRepository.findById(answerId);
        if (existingAnswerOption.isPresent()) {
            AnswerOption answerOption = existingAnswerOption.get();
            answerOption.setCorrectAnswer(answerOptionDTO.getCorrectAnswer());
            answerOption.setContent(answerOptionDTO.getContent());
            answerOption.setAttachments(answerOptionDTO.getAttachments());
            AnswerOption updatedAnswerOption = answerOptionRepository.save(answerOption);
            return answerOptionMapper.toDTO(updatedAnswerOption);
        }
        throw new RuntimeException("AnswerOption not found");
    }

    // Xóa AnswerOption theo ID
    public void deleteAnswerOption(Integer answerId) {
        Optional<AnswerOption> existingAnswerOption = answerOptionRepository.findById(answerId);
        if (existingAnswerOption.isPresent()) {
            answerOptionRepository.delete(existingAnswerOption.get());
        } else {
            throw new RuntimeException("AnswerOption not found");
        }
    }
}