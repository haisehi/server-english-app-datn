package com.example.english_learning_server.controller;

import com.example.english_learning_server.dto.AttendanceDTO;
import com.example.english_learning_server.entity.UserAttendance;
import com.example.english_learning_server.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<?> markAttendance(@RequestParam Integer userId) {
        String result = attendanceService.markAttendance(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/streak/{userId}")
    public ResponseEntity<?> getStreak(@PathVariable Integer userId) {
        int streak = attendanceService.calculateStreak(userId);
        return ResponseEntity.ok(streak);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getAttendanceHistory(@PathVariable Integer userId) {
        List<UserAttendance> history = attendanceService.getAttendanceHistory(userId);

        List<AttendanceDTO> dtoList = history.stream()
                .map(a -> new AttendanceDTO(
                        a.getId(),
                        a.getAttendanceDate().toString(),
                        a.getStatus(),
                        a.getUser().getId()
                ))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

}
