package com.example.english_learning_server.service;

import com.example.english_learning_server.entity.Token;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.entity.Vocabulary;
import com.example.english_learning_server.entity.VocabularyUser;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import com.example.english_learning_server.reponsitory.VocabularyRepository;
import com.example.english_learning_server.reponsitory.VocabularyUserRepository;
import com.example.english_learning_server.reponsitory.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularyUserService {

    @Autowired
    private VocabularyUserRepository vocabularyUserRepository;

    @Autowired
    private UserReponsitory userRepository;

    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public VocabularyUser saveVocabularyForUser(Integer userId, Integer vocabId, String progress) {
        // Tìm User và Vocabulary từ ID
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Vocabulary vocabulary = vocabularyRepository.findById(vocabId).orElseThrow(() -> new RuntimeException("Vocabulary not found"));

        // Tạo một đối tượng VocabularyUser mới
        VocabularyUser vocabularyUser = VocabularyUser.builder()
                .user(user)
                .vocabulary(vocabulary)
                .progress(progress)
                .build();

        // Lưu vào cơ sở dữ liệu
        return vocabularyUserRepository.save(vocabularyUser);
    }

    // Lấy tất cả VocabularyUser
    public List<VocabularyUser> getAllVocabularyUsers() {
        return vocabularyUserRepository.findAll();
    }

    // Lấy VocabularyUser theo id
    public VocabularyUser getVocabularyUserById(Long id) {
        return vocabularyUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VocabularyUser not found"));
    }

    // Lấy danh sách VocabularyUser theo userId
    public List<VocabularyUser> getVocabularyUsersByUserId(Integer userId) {
        return vocabularyUserRepository.findByUserId(userId);
    }

    // Lấy danh sách VocabularyUser theo Access Token (userId sẽ được lấy từ token)
    public List<VocabularyUser> getVocabularyUsersByToken(String token) {
        Token tokenEntity = tokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Token not found"));
        User user = tokenEntity.getUser();
        return vocabularyUserRepository.findByUserId(user.getId());
    }

    // Xóa VocabularyUser theo id
    public void deleteVocabularyUserById(Long id) {
        VocabularyUser vocabularyUser = vocabularyUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VocabularyUser not found"));

        vocabularyUserRepository.delete(vocabularyUser);
    }
}
