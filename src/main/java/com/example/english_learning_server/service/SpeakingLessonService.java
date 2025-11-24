package com.example.english_learning_server.service;


import com.example.english_learning_server.dto.SpeakingLessonDTO;
import com.example.english_learning_server.dto.SpeakingLessonStatusDTO;
import com.example.english_learning_server.entity.SpeakingLesson;
import com.example.english_learning_server.entity.SpeakingTopic;
import com.example.english_learning_server.entity.SpeakingUserScore;
import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.reponsitory.SpeakingLessonRepository;
import com.example.english_learning_server.reponsitory.SpeakingTopicRepository;
import com.example.english_learning_server.reponsitory.SpeakingUserScoreRepository;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpeakingLessonService {
    private final SpeakingLessonRepository lessonRepository;
    private final SpeakingTopicRepository topicRepository;

    private final UserReponsitory userRepository;
    private final SpeakingUserScoreRepository scoreRepository;

    public SpeakingLessonService(
            SpeakingLessonRepository lessonRepository,
            SpeakingTopicRepository topicRepository,
            UserReponsitory userRepository,
            SpeakingUserScoreRepository scoreRepository
    ) {
        this.lessonRepository = lessonRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.scoreRepository = scoreRepository;
    }


    public SpeakingLesson createLesson(SpeakingLessonDTO dto) {
        SpeakingTopic topic = topicRepository.findById(dto.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));
        SpeakingLesson lesson = new SpeakingLesson();
        lesson.setTopic(topic);
        lesson.setTitle(dto.getTitle());
        lesson.setDescription(dto.getDescription());
        return lessonRepository.save(lesson);
    }

    public List<SpeakingLesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public List<SpeakingLessonStatusDTO> getLessonsByTopicForCurrentUser(Long topicId) {
        // 1. Lấy user từ access token
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (principal instanceof UserDetails)
                ? ((UserDetails) principal).getUsername()
                : principal.toString();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Lấy tất cả lesson theo topic
        List<SpeakingLesson> lessons = lessonRepository.findByTopicId(topicId);

        // 3. Check trạng thái từng lesson theo user
        List<SpeakingLessonStatusDTO> result = new ArrayList<>();

        for (SpeakingLesson lesson : lessons) {

            Optional<SpeakingUserScore> scoreOpt =
                    scoreRepository.findByUserAndLesson(user.getId(), lesson.getId());

            SpeakingLessonStatusDTO dto = new SpeakingLessonStatusDTO();
            dto.setLessonId(lesson.getId());
            dto.setTitle(lesson.getTitle());
            dto.setDescription(lesson.getDescription());

            if (scoreOpt.isPresent()) {
                SpeakingUserScore s = scoreOpt.get();
                dto.setJoined(true);
                dto.setTotalCorrect(s.getTotalCorrect());
                dto.setTotalSentences(s.getTotalSentences());
                dto.setPass(s.getIsPass());
            } else {
                dto.setJoined(false);
            }

            result.add(dto);
        }

        return result;
    }

}
