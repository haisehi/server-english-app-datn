package com.example.english_learning_server.converter;
import com.example.english_learning_server.dto.VocabularyDTO;
import com.example.english_learning_server.entity.Vocabulary;
import org.springframework.stereotype.Component;

@Component
public class VocabularyMapper {

    // Chuyển đổi từ Entity Vocabulary sang DTO
    public VocabularyDTO toDTO(Vocabulary vocabulary) {
        if (vocabulary == null) {
            return null;
        }
        return new VocabularyDTO(
                vocabulary.getVocabId(),
                vocabulary.getWord(),
                vocabulary.getMeaning(),
                vocabulary.getDescription(),
                vocabulary.getImage(),
                vocabulary.getAudio(),
                vocabulary.getLesson() != null ? vocabulary.getLesson().getLessonId() : null
        );
    }

    // Chuyển đổi từ DTO sang Entity Vocabulary
    public Vocabulary toEntity(VocabularyDTO vocabularyDTO) {
        if (vocabularyDTO == null) {
            return null;
        }
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setVocabId(vocabularyDTO.getVocabId());
        vocabulary.setWord(vocabularyDTO.getWord());
        vocabulary.setMeaning(vocabularyDTO.getMeaning());
        vocabulary.setDescription(vocabularyDTO.getDescription());
        vocabulary.setImage(vocabularyDTO.getImage());
        vocabulary.setAudio(vocabularyDTO.getAudio());
        // Không set lesson vì lessonId sẽ được thiết lập trong service
        return vocabulary;
    }
}