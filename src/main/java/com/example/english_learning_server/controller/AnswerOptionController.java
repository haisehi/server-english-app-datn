package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.AnswerOptionDTO;
import com.example.english_learning_server.service.AnswerOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answer-options")
public class AnswerOptionController {

    @Autowired
    private AnswerOptionService answerOptionService;

    // Thêm AnswerOption
    @PostMapping
    public ResponseEntity<AnswerOptionDTO> addAnswerOption(@RequestBody AnswerOptionDTO answerOptionDTO) {
        AnswerOptionDTO createdAnswerOption = answerOptionService.addAnswerOption(answerOptionDTO);
        return new ResponseEntity<>(createdAnswerOption, HttpStatus.CREATED);
    }

    // Hiện tất cả AnswerOptions
    @GetMapping
    public ResponseEntity<List<AnswerOptionDTO>> getAllAnswerOptions() {
        List<AnswerOptionDTO> answerOptions = answerOptionService.getAllAnswerOptions();
        return new ResponseEntity<>(answerOptions, HttpStatus.OK);
    }

    // Hiện AnswerOptions theo ID câu hỏi
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<AnswerOptionDTO>> getAnswerOptionsByQuestionId(@PathVariable Integer questionId) {
        List<AnswerOptionDTO> answerOptions = answerOptionService.getAnswerOptionsByQuestionId(questionId);
        return new ResponseEntity<>(answerOptions, HttpStatus.OK);
    }

    // Cập nhật AnswerOption theo ID
    @PutMapping("/{answerId}")
    public ResponseEntity<AnswerOptionDTO> updateAnswerOption(
            @PathVariable Integer answerId,
            @RequestBody AnswerOptionDTO answerOptionDTO) {
        AnswerOptionDTO updatedAnswerOption = answerOptionService.updateAnswerOption(answerId, answerOptionDTO);
        return new ResponseEntity<>(updatedAnswerOption, HttpStatus.OK);
    }

    // Xóa AnswerOption theo ID
    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswerOption(@PathVariable Integer answerId) {
        answerOptionService.deleteAnswerOption(answerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}