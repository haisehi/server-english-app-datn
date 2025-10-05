package com.example.english_learning_server.converter;

import com.example.english_learning_server.dto.VocabularyUserDTO;
import com.example.english_learning_server.entity.Vocabulary;
import com.example.english_learning_server.entity.VocabularyUser;
import com.example.english_learning_server.reponsitory.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VocabularyUserMapper {
    @Autowired
    private VocabularyRepository vocabularyRepository;
    public VocabularyUserDTO toDTO(VocabularyUser vocabularyUser) {
        Vocabulary vocabulary = vocabularyUser.getVocabulary();
        return VocabularyUserDTO.builder()
                .id(vocabularyUser.getId())
                .userId(vocabularyUser.getUser().getId())
                .vocabId(vocabularyUser.getVocabulary().getVocabId())
                .progress(vocabularyUser.getProgress())
                .word(vocabulary.getWord())  // Thêm từ từ bảng Vocabulary
                .meaning(vocabulary.getMeaning())  // Thêm nghĩa từ bảng Vocabulary
                .build();
    }

    public VocabularyUser toEntity(VocabularyUserDTO vocabularyUserDTO) {
        VocabularyUser vocabularyUser = new VocabularyUser();
        vocabularyUser.setId(vocabularyUserDTO.getId());
        vocabularyUser.setProgress(vocabularyUserDTO.getProgress());
        // Bạn cần truy vấn User và Vocabulary từ database để set giá trị cho các đối tượng này
        // Ví dụ: vocabularyUser.setUser(userRepository.findById(vocabularyUserDTO.getUserId()).get());
        // Ví dụ: vocabularyUser.setVocabulary(vocabularyRepository.findById(vocabularyUserDTO.getVocabId()).get());
        return vocabularyUser;
    }
}