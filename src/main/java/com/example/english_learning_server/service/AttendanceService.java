package com.example.english_learning_server.service;

import com.example.english_learning_server.entity.User;
import com.example.english_learning_server.entity.UserAttendance;
import com.example.english_learning_server.entity.UserReward;
import com.example.english_learning_server.reponsitory.UserAttendanceRepository;
import com.example.english_learning_server.reponsitory.UserReponsitory;
import com.example.english_learning_server.reponsitory.UserRewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final UserAttendanceRepository attendanceRepo;
    private final UserReponsitory userRepo;
    private final UserRewardRepository rewardRepo;

    public String markAttendance(Integer userId) {
        LocalDate today = LocalDate.now();

        // Đã điểm danh trong ngày
        if (attendanceRepo.existsByUserIdAndAttendanceDate(userId, today)) {
            return "already_marked";
        }

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserAttendance attendance = new UserAttendance();
        attendance.setUser(user);
        attendance.setAttendanceDate(today);
        attendanceRepo.save(attendance);

        // Cập nhật phần thưởng nếu đạt streak
        int streak = calculateStreak(userId);
        checkRewardMilestones(user, streak);

        return "marked_successfully";
    }

    public int calculateStreak(Integer userId) {
        List<UserAttendance> records = attendanceRepo.findByUserIdOrderByAttendanceDateDesc(userId);
        int streak = 0;
        LocalDate today = LocalDate.now();

        for (UserAttendance record : records) {
            if (record.getAttendanceDate().equals(today.minusDays(streak))) {
                streak++;
            } else {
                break;
            }
        }
        return streak;
    }

    private void checkRewardMilestones(User user, int streak) {
        int[] milestones = {7, 30, 100};
        for (int milestone : milestones) {
            if (streak == milestone && !hasReward(user.getId(), milestone)) {
                UserReward reward = new UserReward();
                reward.setUser(user);
                reward.setStreakDays(milestone);
                reward.setRewardType("badge");
                rewardRepo.save(reward);
            }
        }
    }

    private boolean hasReward(Integer userId, int milestone) {
        return rewardRepo.findByUserId(userId)
                .stream()
                .anyMatch(r -> r.getStreakDays() == milestone);
    }

    public List<UserAttendance> getAttendanceHistory(Integer userId) {
        return attendanceRepo.findByUserIdOrderByAttendanceDateDesc(userId);
    }
}
