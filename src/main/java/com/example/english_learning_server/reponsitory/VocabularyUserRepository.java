package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.VocabularyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocabularyUserRepository extends JpaRepository<VocabularyUser, Long> {
    // Tìm VocabularyUser theo userId
    List<VocabularyUser> findByUserId(Integer userId);
}