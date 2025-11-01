package com.example.english_learning_server.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "user_attendance",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "attendance_date"})}
)
public class UserAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;

    @Column(name = "status", nullable = false)
    private String status = "present"; // present | absent

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
