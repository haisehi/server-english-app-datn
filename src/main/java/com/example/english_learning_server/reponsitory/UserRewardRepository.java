package com.example.english_learning_server.reponsitory;

import com.example.english_learning_server.entity.UserReward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRewardRepository extends JpaRepository<UserReward, Long> {
    List<UserReward> findByUserId(Integer userId);
}