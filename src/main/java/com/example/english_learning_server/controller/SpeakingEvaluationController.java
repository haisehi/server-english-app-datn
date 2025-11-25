package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.*;
import com.example.english_learning_server.service.SpeakingEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/speaking")
public class SpeakingEvaluationController {

    private final SpeakingEvaluationService evaluationService;

    public SpeakingEvaluationController(SpeakingEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // So sánh 1 câu (ephemeral)
    @PostMapping("/compare")
    public ResponseEntity<SpeakingCompareResponseDTO> compareSentence(@RequestBody SpeakingCompareRequestDTO req) {
        SpeakingCompareResponseDTO res = evaluationService.compareSentence(req);
        return ResponseEntity.ok(res);
    }

    // Nộp kết quả cuối bài và lưu điểm
    @PostMapping("/submit")
    public ResponseEntity<SpeakingSubmitResponseDTO> submitLessonResult(@RequestBody SpeakingSubmitRequestDTO req) {
        SpeakingSubmitResponseDTO resp = evaluationService.submitResults(req);
        return ResponseEntity.ok(resp);
    }
}
