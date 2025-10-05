package com.example.english_learning_server.service;

import com.example.english_learning_server.entity.Course;
import com.example.english_learning_server.entity.Lesson;
import com.example.english_learning_server.entity.Vocabulary;
import com.example.english_learning_server.reponsitory.LessonRepository;
import com.example.english_learning_server.reponsitory.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VocabularyService {

    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Autowired
    private LessonRepository lessonRepository;

    // Thêm mới Vocabulary
    public Vocabulary addVocabulary(Vocabulary vocabulary) {
        return vocabularyRepository.save(vocabulary);
    }

    // Thêm Vocabulary vào Lesson
    public Vocabulary addVocabularyToLesson(Vocabulary vocabulary, Integer lessonId) {
        // Tìm Lesson theo lessonId
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
        if (lessonOptional.isEmpty()) {
            throw new IllegalArgumentException("Lesson không tồn tại với ID: " + lessonId);
        }

        // Lấy Lesson và thiết lập lesson cho vocabulary
        Lesson lesson = lessonOptional.get();
        vocabulary.setLesson(lesson);

        // Lưu Vocabulary vào lesson
        return vocabularyRepository.save(vocabulary);
    }

    // Lấy tất cả Vocabulary
    public List<Vocabulary> getAllVocabularies() {
        return vocabularyRepository.findAll();
    }

    // Lấy Vocabulary theo vocabId
    public Vocabulary getVocabularyById(Integer vocabId) {
        Optional<Vocabulary> vocabulary = vocabularyRepository.findById(vocabId);
        return vocabulary.orElseThrow(() -> new RuntimeException("Vocabulary not found with id " + vocabId));
    }

    // Lấy Vocabulary theo word
    public Vocabulary getVocabularyByWord(String word) {
        return vocabularyRepository.findByWord(word).orElseThrow(() -> new RuntimeException("Vocabulary not found with word " + word));
    }

    // Lấy danh sách Vocabulary theo lessonId
    public List<Vocabulary> getVocabulariesByLessonId(Integer lessonId) {
        return vocabularyRepository.findByLesson_LessonId(lessonId);
    }

    // Cập nhật Vocabulary theo ID
    public Vocabulary updateVocabulary(Integer vocabId, Vocabulary updatedVocabulary) {
        Vocabulary existingVocabulary = vocabularyRepository.findById(vocabId)
                .orElseThrow(() -> new RuntimeException("Vocabulary không tồn tại với id " + vocabId));

        existingVocabulary.setWord(updatedVocabulary.getWord());
        existingVocabulary.setMeaning(updatedVocabulary.getMeaning());
        existingVocabulary.setDescription(updatedVocabulary.getDescription());
        existingVocabulary.setImage(updatedVocabulary.getImage());
        existingVocabulary.setAudio(updatedVocabulary.getAudio());

        return vocabularyRepository.save(existingVocabulary);
    }

    // Xóa Vocabulary theo ID
    public void deleteVocabulary(Integer vocabId) {
        if (!vocabularyRepository.existsById(vocabId)) {
            throw new RuntimeException("Vocabulary không tồn tại với id " + vocabId);
        }
        vocabularyRepository.deleteById(vocabId);
    }

}