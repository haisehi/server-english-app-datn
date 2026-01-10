package com.example.english_learning_server.service;

import com.example.english_learning_server.dto.*;
import com.example.english_learning_server.entity.*;
import com.example.english_learning_server.reponsitory.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SpeakingEvaluationService {

    private final SpeakingSentenceRepository sentenceRepository;
    private final SpeakingLessonRepository lessonRepository;
    private final UserReponsitory userRepository;
    private final SpeakingUserScoreRepository scoreRepository;

    // ngưỡng mặc định = 0.8 (80%)
    private static final double SPEAKING_THRESHOLD = 0.8;

    public SpeakingEvaluationService(SpeakingSentenceRepository sentenceRepository,
                                     SpeakingLessonRepository lessonRepository,
                                     UserReponsitory userRepository,
                                     SpeakingUserScoreRepository scoreRepository) {
        this.sentenceRepository = sentenceRepository;
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.scoreRepository = scoreRepository;
    }

    // ========== compare one sentence ==========
    public SpeakingCompareResponseDTO compareSentence(SpeakingCompareRequestDTO req) {
        SpeakingCompareResponseDTO res = new SpeakingCompareResponseDTO();

        SpeakingSentence sentence = sentenceRepository.findById(req.getSentenceId())
                .orElseThrow(() -> new RuntimeException("Sentence not found"));

        String expected = normalize(sentence.getSentenceEn());
        String spoken = normalize(req.getUserText());

        double similarity = similarity(expected, spoken);
        boolean isCorrect = similarity >= SPEAKING_THRESHOLD; // 0.8

        res.setExpected(sentence.getSentenceEn());
        res.setSimilarity(similarity);
        res.setCorrect(isCorrect);
        res.setFeedback(isCorrect ? "Correct" : "Not correct");
        return res;
    }


    // ========== submit final results and save ==========
    @Transactional
    public SpeakingSubmitResponseDTO submitResults(SpeakingSubmitRequestDTO req) {

        // Lấy user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = (principal instanceof UserDetails)
                ? ((UserDetails) principal).getUsername()
                : principal.toString();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Lấy bài học
        SpeakingLesson lesson = lessonRepository.findById(req.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        int total = req.getResults() == null ? 0 : req.getResults().size();
        int correctCount = 0;

        // So sánh từng câu
        if (req.getResults() != null) {
            for (var item : req.getResults()) {

                Optional<SpeakingSentence> sOpt = sentenceRepository.findById(item.getSentenceId());
                if (sOpt.isEmpty()) continue;

                String expected = normalize(sOpt.get().getSentenceEn());
                String spoken = normalize(item.getUserText());

                double sim = similarity(expected, spoken);

                if (sim >= SPEAKING_THRESHOLD) {
                    correctCount++;
                }
            }
        }

        // Tính phần trăm
        int percent = total == 0 ? 0 : (int) Math.round((double) correctCount * 100 / total);

        // ⭐ Pass khi > 50%
        boolean isPass = percent > 50;

        // Kiểm tra bản ghi cũ
        Optional<SpeakingUserScore> oldScoreOpt =
                scoreRepository.findByUserAndLesson(user.getId(), lesson.getId());

        SpeakingUserScore score;

        if (oldScoreOpt.isPresent()) {
            score = oldScoreOpt.get();
            score.setTotalCorrect(correctCount);
            score.setTotalSentences(total);
            score.setIsPass(isPass);
        } else {
            score = new SpeakingUserScore();
            score.setUser(user);
            score.setLesson(lesson);
            score.setTotalCorrect(correctCount);
            score.setTotalSentences(total);
            score.setIsPass(isPass);
        }

        SpeakingUserScore saved = scoreRepository.save(score);

        // Trả kết quả
        SpeakingSubmitResponseDTO resp = new SpeakingSubmitResponseDTO();
        resp.setId(saved.getId());
        resp.setLessonId(lesson.getId());
        resp.setUserId(user.getId());
        resp.setTotalCorrect(correctCount);
        resp.setTotalSentences(total);
        resp.setPercent(percent);
        resp.setIsPass(isPass);
        resp.setCreatedAt(saved.getCreatedAt());
        return resp;
    }



// ------------------ helper functions ------------------

    private String normalize(String text) {
        if (text == null) return "";

        // Chuẩn hóa unicode + loại dấu câu + thu gọn khoảng trắng
        return text.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")  // remove punctuation
                .replaceAll("\\s+", " ")
                .trim();
    }

    private double similarity(String a, String b) {
        if (a.equals(b)) return 1.0;
        if (a.isEmpty() || b.isEmpty()) return 0.0;

        int dist = levenshteinDistance(a, b);
        int maxLen = Math.max(a.length(), b.length());

        // similarity = 1 - distance / maxLength
        return 1.0 - ((double) dist / maxLen);
    }

//    đo lường mức độ sai khác
    private int levenshteinDistance(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n == 0) return m;
        if (m == 0) return n;

        int[] prev = new int[m + 1]; //kết quả tính toán của hàng trước
        int[] curr = new int[m + 1]; //kết quả tính toán của hàng hiện tại

        for (int j = 0; j <= m; j++) prev[j] = j;

        for (int i = 1; i <= n; i++) {
            curr[0] = i;
            for (int j = 1; j <= m; j++) {
                int cost = (s.charAt(i - 1) == t.charAt(j - 1)) ? 0 : 1;
                curr[j] = Math.min(
                        Math.min(curr[j - 1] + 1, prev[j] + 1),
                        prev[j - 1] + cost
                );
            }
            System.arraycopy(curr, 0, prev, 0, m + 1);
        }

        return prev[m];
    }

}
