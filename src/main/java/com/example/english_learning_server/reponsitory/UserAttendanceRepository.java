package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.UserAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface UserAttendanceRepository extends JpaRepository<UserAttendance, Long> {
    boolean existsByUserIdAndAttendanceDate(Integer userId, LocalDate date);
    List<UserAttendance> findByUserIdOrderByAttendanceDateDesc(Integer userId);
}
