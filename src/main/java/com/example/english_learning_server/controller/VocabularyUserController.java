package com.example.english_learning_server.controller;

import com.example.english_learning_server.entity.VocabularyUser;
import com.example.english_learning_server.dto.VocabularyUserDTO;
import com.example.english_learning_server.converter.VocabularyUserMapper;
import com.example.english_learning_server.service.VocabularyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/vocabularyUser")
public class VocabularyUserController {

    @Autowired
    private VocabularyUserService vocabularyUserService;

    @Autowired
    private VocabularyUserMapper vocabularyUserMapper;

    @PostMapping
    public VocabularyUser addVocabularyForUser(@RequestBody Map<String, Object> requestBody) {
        Integer userId = (Integer) requestBody.get("userId");
        Integer vocabId = (Integer) requestBody.get("vocabId");
        String progress = (String) requestBody.get("progress");

        return vocabularyUserService.saveVocabularyForUser(userId, vocabId, progress);
    }


    @GetMapping("/all")
    public List<VocabularyUserDTO> getAllVocabularyUsers() {
        List<VocabularyUser> vocabularyUsers = vocabularyUserService.getAllVocabularyUsers();
        return vocabularyUsers.stream()
                .map(vocabularyUserMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public VocabularyUserDTO getVocabularyUserById(@PathVariable Long id) {
        VocabularyUser vocabularyUser = vocabularyUserService.getVocabularyUserById(id);
        return vocabularyUserMapper.toDTO(vocabularyUser);
    }

    @GetMapping("/user")
    public List<VocabularyUserDTO> getVocabularyUsersByToken(@RequestHeader("Authorization") String token) {
        String accessToken = token.replace("Bearer ", "");
        List<VocabularyUser> vocabularyUsers = vocabularyUserService.getVocabularyUsersByToken(accessToken);
        return vocabularyUsers.stream()
                .map(vocabularyUserMapper::toDTO)
                .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVocabularyUserById(@PathVariable Long id) {
        try {
            vocabularyUserService.deleteVocabularyUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body("VocabularyUser deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("VocabularyUser not found");
        }
    }
}
