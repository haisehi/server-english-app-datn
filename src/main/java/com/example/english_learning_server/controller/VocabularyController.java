package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.VocabularyDTO;
import com.example.english_learning_server.entity.Vocabulary;
import com.example.english_learning_server.converter.VocabularyMapper;
import com.example.english_learning_server.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vocabularies")
public class VocabularyController {

    @Autowired
    private VocabularyService vocabularyService;

    @Autowired
    private VocabularyMapper vocabularyMapper;

    // API thêm Vocabulary
    @PostMapping
    public ResponseEntity<VocabularyDTO> addVocabulary(@RequestBody Map<String, Object> vocabularyData) {
        // Lấy lessonId và các thuộc tính của Vocabulary từ map
        Integer lessonId = (Integer) vocabularyData.get("lessonId");
        String word = (String) vocabularyData.get("word");
        String meaning = (String) vocabularyData.get("meaning");
        String description = (String) vocabularyData.get("description");
        String image = (String) vocabularyData.get("image");
        String audio = (String) vocabularyData.get("audio");

        // Tạo đối tượng Vocabulary và set các thuộc tính
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setWord(word);
        vocabulary.setMeaning(meaning);
        vocabulary.setDescription(description);
        vocabulary.setImage(image);
        vocabulary.setAudio(audio);

        // Thêm Vocabulary vào lesson tương ứng
        Vocabulary newVocabulary = vocabularyService.addVocabularyToLesson(vocabulary, lessonId);

        // Chuyển đổi từ Entity sang DTO và trả về
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDTO(newVocabulary);
        return new ResponseEntity<>(vocabularyDTO, HttpStatus.CREATED);
    }

    // API hiển thị danh sách tất cả Vocabulary
    @GetMapping
    public ResponseEntity<List<VocabularyDTO>> getAllVocabularies() {
        List<Vocabulary> vocabularies = vocabularyService.getAllVocabularies();
        List<VocabularyDTO> vocabularyDTOs = vocabularies.stream()
                .map(vocabularyMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vocabularyDTOs);
    }

    // API hiển thị Vocabulary theo vocabId
    @GetMapping("/{vocabId}")
    public ResponseEntity<VocabularyDTO> getVocabularyById(@PathVariable Integer vocabId) {
        Vocabulary vocabulary = vocabularyService.getVocabularyById(vocabId);
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDTO(vocabulary);
        return ResponseEntity.ok(vocabularyDTO);
    }

    // API hiển thị Vocabulary theo word
    @GetMapping("/word/{word}")
    public ResponseEntity<VocabularyDTO> getVocabularyByWord(@PathVariable String word) {
        Vocabulary vocabulary = vocabularyService.getVocabularyByWord(word);
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDTO(vocabulary);
        return ResponseEntity.ok(vocabularyDTO);
    }

    // API hiển thị Vocabulary theo lesson_id
    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<VocabularyDTO>> getVocabulariesByLessonId(@PathVariable Integer lessonId) {
        List<Vocabulary> vocabularies = vocabularyService.getVocabulariesByLessonId(lessonId);
        List<VocabularyDTO> vocabularyDTOs = vocabularies.stream()
                .map(vocabularyMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vocabularyDTOs);
    }

    // API cập nhật Vocabulary theo ID
    @PutMapping("/{vocabId}")
    public ResponseEntity<VocabularyDTO> updateVocabulary(
            @PathVariable Integer vocabId,
            @RequestBody VocabularyDTO updatedVocabularyDTO) {
        // Chuyển đổi DTO sang Entity
        Vocabulary updatedVocabulary = vocabularyMapper.toEntity(updatedVocabularyDTO);

        // Cập nhật Vocabulary
        Vocabulary updatedEntity = vocabularyService.updateVocabulary(vocabId, updatedVocabulary);

        // Chuyển Entity sang DTO và trả về
        VocabularyDTO vocabularyDTO = vocabularyMapper.toDTO(updatedEntity);
        return ResponseEntity.ok(vocabularyDTO);
    }

    // API xóa Vocabulary theo ID
    @DeleteMapping("/{vocabId}")
    public ResponseEntity<Void> deleteVocabulary(@PathVariable Integer vocabId) {
        vocabularyService.deleteVocabulary(vocabId);
        return ResponseEntity.noContent().build();
    }

}
