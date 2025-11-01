package com.example.english_learning_server.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_reward")
public class UserReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "streak_days")
    private int streakDays; // Mốc đạt được

    @Column(name = "reward_type")
    private String rewardType; // "coin", "badge", "certificate"

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
